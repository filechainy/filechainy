import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperlinkExtractor {
    private static final Pattern hyperlinkPattern = Pattern.compile("<a[^>]*href\\s*=\\s*\"([^\"]+)\"");
    private static final String fileName = "urls.txt";

    public static void main(String[] args) throws IOException {
        List<String> urls = new LinkedList<>();
        Set<String> uniqueUrls = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String urlString;
            while ((urlString = br.readLine()) != null) {
                urls.add(urlString);
                uniqueUrls.add(urlString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String urlString : urls) {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        Matcher matcher = hyperlinkPattern.matcher(inputLine);
                        while (matcher.find()) {
                            String hyperlink = matcher.group(1);
                            if (!uniqueUrls.contains(hyperlink) && (hyperlink.startsWith("http://") || hyperlink.startsWith("https://"))) {
                                bw.newLine();
                                bw.write(hyperlink);                                
                                uniqueUrls.add(hyperlink);
                                System.out.println(hyperlink);
                            }
                        }
                    }
                }
            }
        }
    }
}
