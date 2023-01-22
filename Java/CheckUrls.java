import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.File;

public class CheckUrls {
    public static void main(String[] args) throws IOException {

        /*/

        This program read the text file 'urls.txt'  
        and check each URL against all others for verify if they host the same files.
        If so, a small GET request will be performed to a log server.
        The files that will be checked must be in the 'hash' folder.

        /*/

        // Check each URL against all others listing the files in 'hash' folder
        List<String> urls = readFile("urls.txt");
        File folder = new File("hash");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < urls.size(); i++) {
            for (int j = i + 1; j < urls.size(); j++) {                
                if (isSameFile(urls.get(i), urls.get(j))) {
                    for (int k = 0; k < listOfFiles.length; k++) {
                        if (listOfFiles[k].isFile()) {
                            System.out.println("File " + listOfFiles[k].getName());
                            makeHttpGetRequest(urls.get(i) + "/hash/" + listOfFiles[k].getName(), urls.get(j) + "/hash/" + listOfFiles[k].getName());
                        }
                    }                        
                }                
            }
        }
    }

    private static List<String> readFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    private static boolean isSameFile(String url1, String url2) throws IOException {
        String hash1 = getFileHash(url1);
        String hash2 = getFileHash(url2);
        return hash1.equals(hash2);
    }

    private static String getFileHash(String url) throws IOException {
        try (InputStream inputStream = new URL(url).openStream()) {
            byte[] fileBytes = inputStream.readAllBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(fileBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            //throw new IOException("Error getting file hash", e);      
            return url;      
        }
        
    }

    private static void makeHttpGetRequest(String url1, String url2) throws IOException {
        URL url = new URL("http://localhost/file7menta/files.php?url=" + url1 + "," + url2);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        System.out.println("Response status code: " + responseCode);
    }
}
