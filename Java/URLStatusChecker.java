import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLStatusChecker {

    public static void main(String[] args) {
        // Create a list of URLs to check
        List<String> urls = new ArrayList<>();
        urls.add("http://www.example.com");
        urls.add("http://www.example.org");
        urls.add("http://www.example.net");

        // Check the status of each URL
        for (String url : urls) {
            try {
                long startTime = System.currentTimeMillis();
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                int responseCode = connection.getResponseCode();
                long endTime = System.currentTimeMillis();
                long latency = endTime - startTime;

                if (responseCode == 200) {
                    System.out.println(url + " is online (latency: " + latency + "ms)");
                } else {
                    System.out.println(url + " is offline (latency: " + latency + "ms)");
                }
            } catch (IOException e) {
                System.out.println(url + " is offline");
            }
        }
    }
}