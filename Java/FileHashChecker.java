import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHashChecker {

    public static void main(String[] args) {
        // Specify the folder to check
        File folder = new File("D:/a/");

        // Get a list of all the files in the folder
        File[] files = folder.listFiles();

        // Check the hash of each file
        for (File file : files) {
            try {
                // Calculate the SHA-1 hash of the file
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[1024];
                int nread = 0;
                while ((nread = fis.read(data)) != -1) {
                    digest.update(data, 0, nread);
                }
                byte[] hashBytes = digest.digest();

                // Convert the hash to a hexadecimal string
                StringBuilder sb = new StringBuilder();
                for (byte b : hashBytes) {
                    sb.append(String.format("%02x", b));
                }
                String hash = sb.toString();

                // Compare the hash to the filename
                if (hash.equals(file.getName())) {
                    System.out.println(file.getName() + " has a valid hash");
                } else {
                    System.out.println(file.getName() + " has an invalid hash");
                }

            } catch (NoSuchAlgorithmException | IOException e) {
                System.out.println("Error calculating hash for " + file.getName());
            }
        }
    }
}
