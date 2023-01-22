import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class CheckURLs4 {
    public static void main(String[] args) throws Exception {
        // Read file containing URLs
        File file = new File("urls.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Create a list to store URLs
        List<URL> urls = new ArrayList<>();

        // Read URLs from file and add to list
        String url;
        while ((url = br.readLine()) != null) {
            urls.add(new URL(url));
        }

        // Check each URL against all others
        for (int i = 0; i < urls.size(); i++) {
            for (int j = i + 1; j < urls.size(); j++) {
                // Download cat.jpg from each URL
                byte[] file1 = downloadFile(urls.get(i), "/hash/cat.jpg");
                byte[] file2 = downloadFile(urls.get(j), "/hash/cat.jpg");

                // Hash the contents of the two files
                String hash1 = getHash(file1);
                String hash2 = getHash(file2);

                // Compare the hashes of the two files
                if (hash1.equals(hash2)) {
                    System.out.println("Files are identical: " + urls.get(i) + " and " + urls.get(j));
                } else {
                    System.out.println("Files are not identical: " + urls.get(i) + " and " + urls.get(j));
                }
            }
        }
    }

    // Method to download a file from a URL
    public static byte[] downloadFile(URL url, String fileName) throws IOException {
        InputStream in = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.close();
        in.close();
        return out.toByteArray();
    }

    // Method to generate a hash of a byte array using SHA-256
    public static String getHash(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data);
        byte[] hash = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
