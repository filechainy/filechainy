import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.Files;

public class URLDuplicateChecker {
    public static void main(String[] args) throws IOException {
        // File containing URLs
        File file = new File("urls.txt");
        // List to store URLs
        List<String> urls = new ArrayList<>();
        // Read URLs from file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String url;
            while ((url = br.readLine()) != null) {
                urls.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Check each URL against all others
        for (int i = 0; i < urls.size(); i++) {
            for (int j = i + 1; j < urls.size(); j++) {
                try {
                    URL url1 = new URL(urls.get(i));
                    URL url2 = new URL(urls.get(j));
                    URLConnection conn1 = url1.openConnection();
                    URLConnection conn2 = url2.openConnection();
                    String file1 = url1 + "/hash/cat.jpg";
                    String file2 = url2 + "/hash/cat.jpg";
                    InputStream is1 = conn1.getInputStream();
                    InputStream is2 = conn2.getInputStream();
                    byte[] file1Bytes = is1.readAllBytes();
                    byte[] file2Bytes = is2.readAllBytes();
                    if (Arrays.equals(file1Bytes, file2Bytes)) {
                        System.out.println("The file cat.jpg hosted at " + url1 + " and " + url2 + " are identical.");
                    } else {
                        System.out.println("The file cat.jpg hosted at " + url1 + " and " + url2 + " are different.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
