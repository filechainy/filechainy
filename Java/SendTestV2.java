import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.OutputStream;

public class SendTestV2 {
    public static void main(String[] args) {
        File directory = new File("files");
        File[] files = directory.listFiles();
        if (files == null) {
            System.err.println("The directory 'files' does not exist or is empty.");
            return;
        }

        for (File file : files) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file '" + file.getName() + "': " + e.getMessage());
                continue;
            }

            String fileContent = sb.toString();
            String encodedFile = Base64.getEncoder().encodeToString(fileContent.getBytes());

            try {
                URL url = new URL("http://localhost/filechainy/receive.php?contents=" + encodedFile);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setDoOutput(true);

                try (OutputStream os = con.getOutputStream()) {
                    os.write(encodedFile.getBytes());
                }

                int responseCode = con.getResponseCode();
                System.out.println("HTTP GET request sent to " + url + ", response code: " + responseCode);
            } catch (IOException e) {
                System.err.println("An error occurred while sending the GET request for file '" + file.getName() + "': " + e.getMessage());
            }
        }
    }
}
