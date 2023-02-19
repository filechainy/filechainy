import java.io.*;
import java.net.*;
import java.util.*;

public class URLGetter {
    public static void main(String[] args) {
        try {
            // Read the contents of "files.txt"
            BufferedReader filesReader = new BufferedReader(new FileReader("files.txt"));
            List<String> files = new ArrayList<>();
            String line;
            while ((line = filesReader.readLine()) != null) {
                files.add(line);
            }
            filesReader.close();

            // Read the contents of "urls.txt"
            BufferedReader urlsReader = new BufferedReader(new FileReader("urls.txt"));
            List<String> urls = new ArrayList<>();
            while ((line = urlsReader.readLine()) != null) {
                urls.add(line);
            }
            urlsReader.close();

            // Iterate over each URL in "urls.txt" and send a GET request with the file as a parameter
            for (String file : files) {
                for (String url : urls) {
                    URL getURL = new URL(url + "?file=" + URLEncoder.encode(file, "UTF-8"));
                    HttpURLConnection connection = (HttpURLConnection) getURL.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // The GET request was successful, read the response
                        BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = responseReader.readLine()) != null) {
                            System.out.println(line);
                        }
                        responseReader.close();
                    } else {
                        System.err.println("GET request failed with response code: " + responseCode);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file or sending the GET request: " + e.getMessage());
        }
    }
}
