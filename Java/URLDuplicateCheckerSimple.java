import java.io.*;
import java.net.*;
import java.util.*;

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
                    InputStream is1 = conn1.getInputStream();
                    InputStream is2 = conn2.getInputStream();
                    String file1 = url1 + "/hash/cat.jpg";
                    String file2 = url2 + "/hash/cat.jpg";
                    if (file1.equals(file2)) {
                        System.out.println(file1 + " is hosted at both " + url1 + " and " + url2);
                    } else {
                        System.out.println(file1 + " is not hosted at both " + url1 + " and " + url2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
