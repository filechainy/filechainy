import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperlinkDownloader {
  
  private static final String filesDirectory = "files";
  
  public static void main(String[] args) throws IOException {
    URL website = new URL("http://localhost/test/index.php");
    BufferedReader in = new BufferedReader(
        new InputStreamReader(website.openStream()));

    String inputLine;
    Pattern p = Pattern.compile("(?i)<a([^>]+)>(.+?)</a>");
    while ((inputLine = in.readLine()) != null) {
      Matcher m = p.matcher(inputLine);
      while (m.find()) {
        String href = m.group(1);
        if (href.contains("href=")) {
          String link = href.substring(href.indexOf("href=") + 6, href.length() - 1);
          downloadHyperlink(link);
        }
      }
    }
    in.close();
  }

  private static void downloadHyperlink(String link) throws IOException {
    URL url = new URL(link);
    ReadableByteChannel rbc = Channels.newChannel(url.openStream());
    int hashcode = link.hashCode();
    String filename = String.valueOf(hashcode);
    Path path = Paths.get(filesDirectory, filename);
    if (!Files.exists(path)) {
      Files.createDirectories(path.getParent());
    }
    FileOutputStream fos = new FileOutputStream(path.toFile());
    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    fos.close();
  }
}
