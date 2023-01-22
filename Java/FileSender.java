import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileSender {

    public static void main(String[] args) {
        // Specify the folder to read from
        File folder = new File("D:/a/");

        // Get a list of all the files in the folder
        File[] files = folder.listFiles();

        // Read the contents of each file and send it using an HTTP GET request
        for (File file : files) {
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }      
                br.close();                         

                // Send the contents of the file using an HTTP GET request
                sendRequest(sb.toString(), file.getName());
            } catch (IOException e) {
                System.out.println("Error reading file: " + file.getName());
            }         
        }
    }

    private static void sendRequest(String data, String filename) {
        // Create a list of URLs to send the request to
        List<String> urls = new ArrayList<>();
        urls.add("http://localhost/test/index.php");
        urls.add("http://localhost/test/index_test.php");

        // Send the request to each URL
        for (String url : urls) {
            try {
                URL obj = new URL(url + "?hash=" + filename  + "&contents=" + data);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                // Add the data to the query string
                String queryString = "";
                con.setDoOutput(true);
                con.getOutputStream().write(queryString.getBytes("UTF-8"));

                int responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Successfully sent data to " + url);
                } else {
                    System.out.println("Failed to send data to " + url + " (response code: " + responseCode + ")");
                }
            } catch (IOException e) {
                System.out.println("Error sending data to " + url);
            }
        }
    }
}
