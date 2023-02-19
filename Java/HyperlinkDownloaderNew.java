import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperlinkDownloaderNew {
    private static final String URL_REGEX = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static void main(String[] args) throws IOException {
        String url = "http://localhost/test/index.php";
        List<String> hyperlinks = extractHyperlinks(url);
        downloadFiles(hyperlinks);
    }

    private static List<String> extractHyperlinks(String url) throws IOException {
        List<String> hyperlinks = new ArrayList<>();
        URL website = new URL(url);
        BufferedInputStream bis = new BufferedInputStream(website.openStream());
        byte[] buffer = new byte[1024];
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            builder.append(new String(buffer, 0, count));
        }
        bis.close();
        String content = builder.toString();
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            hyperlinks.add(matcher.group());
        }
        return hyperlinks;
    }

    private static void downloadFiles(List<String> hyperlinks) throws IOException {
        for (String link : hyperlinks) {
            URL website = new URL(link);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            String fileName = link.substring(link.lastIndexOf("/") + 1);
            FileOutputStream fos = new FileOutputStream("files/" + fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
        }
    }
}
