import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class URLAvailabilityChecker {

    public static void main(String[] args) {
        // Set the list of URLs to check
        List<String> urls = new ArrayList<>();
        urls.add("https://www.example.com");
        urls.add("https://www.example.org");
        urls.add("https://www.example.net");

        // Check the availability of each URL
        for (String url : urls) {
            boolean isOnline = checkAvailability(url);
            System.out.println(url + " is " + (isOnline ? "online" : "offline"));
        }
    }

    public static boolean checkAvailability(String urlString) {
        try {
            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the connection timeout and read timeout to 5 seconds
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Connect to the URL
            connection.connect();

            // Check the HTTP status code
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                return true;
            } else {
                return false;
            }
        } catch (MalformedURLException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}