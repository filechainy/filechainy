import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;

public class HashChecker {

    public static void main(String[] args) {
        // Directory to read files from
        File directory = new File("hash");
        // File with URLs
        File urlFile = new File("urls.txt");

        // Read URLs from file
        List<String> urlStrings;
        try {
            urlStrings = Files.readAllLines(urlFile.toPath());
        } catch (IOException e) {
            System.err.println("Error: Could not read file " + urlFile.getName());
            return;
        }

        // Iterate through files in directory
        for (File file : directory.listFiles()) {
            if (!file.isFile()) {
                continue;
            }
            byte[] fileHash;
            try {
                fileHash = getSHA1Hash(Files.newInputStream(file.toPath()));
            } catch (IOException e) {
                System.err.println("Error: Could not read file " + file.getName());
                continue;
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Error: SHA-1 algorithm not found");
                return;
            }

            // Iterate through URLs
            boolean matchFound = false;
            for (String urlString : urlStrings) {
                URL url;
                try {
                    url = new URL(urlString + "/hash/" + file.getName());
                } catch (IOException e) {
                    System.err.println("Error: Invalid URL " + urlString);
                    continue;
                }
                byte[] urlHash;
                try {
                    urlHash = getSHA1Hash(url.openStream());
                } catch (IOException e) {
                    System.err.println("Error: Could not fetch file " + file.getName() + " from URL " + url);
                    continue;
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Error: SHA-1 algorithm not found");
                    return;
                }
                if (MessageDigest.isEqual(urlHash, fileHash)) {
                    System.out.println(file.getName() + " has the same hash as the file at the URL " + url);
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                System.out.println(file.getName() + " does not have the same hash as any of the files at the URLs.");
            }
        }
    }

       private static byte[] getSHA1Hash(InputStream is) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        byte[] buffer = new byte[1024];
        int read = 0;
        while ((read = is.read(buffer)) != -1) {
            sha1.update(buffer, 0, read);
        }
        return sha1.digest();
    }
}