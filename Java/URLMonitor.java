import java.io.*;
import java.net.*;

public class URLMonitor {

    public static void main(String[] args) {
        File urlDirectory = new File("urls");
        File[] urlFiles = urlDirectory.listFiles();

        for (File urlFile : urlFiles) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(urlFile));
                String url = reader.readLine();
                reader.close();
                URL urlObject = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
                connection.setRequestMethod("HEAD");
                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error: URL " + url + " is not online.");
                } else {
                    System.out.println("URL " + url + " is online.");
                }
            } catch (IOException e) {
                System.out.println("Error: Unable to connect to URL " + urlFile.getName());
            }
        }
    }
}