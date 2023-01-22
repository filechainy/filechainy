import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckUrls5 {
    public static void main(String[] args) {
        String fileName = "urls.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String url;
            while ((url = br.readLine()) != null) {
                boolean hasCatJpg = checkUrl(url);
                if (hasCatJpg) {
                    System.out.println(url + " has cat.jpg");
                } else {
                    System.out.println(url + " does not have cat.jpg");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace();
        }
    }

  private static boolean checkUrl(String urlString) {
        try {
            URL url = new URL(urlString+"/hash/cat.jpg");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error checking URL: " + urlString);
            e.printStackTrace();
        }
        return false;
  }

}
