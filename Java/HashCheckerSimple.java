import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.URL;
import java.util.Arrays;

public class HashChecker {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Path localHashDir = Paths.get("hash");
        URL remoteFileUrl = new URL("http://localhost/1.png");
        for (Path localFile : Files.newDirectoryStream(localHashDir)) {
            if (!hashEquals(localFile, remoteFileUrl)) {
                System.out.println(localFile.getFileName().toString() + " does not match the hash of the remote file.");
                for(;;){}
            } else {
                System.out.println(localFile.getFileName().toString() + " match the hash of the remote file.");
                for(;;){}
            }
        }
    }

    private static boolean hashEquals(Path file1, URL remoteFileUrl) throws NoSuchAlgorithmException, IOException {
        try (InputStream is1 = Files.newInputStream(file1);
             InputStream is2 = remoteFileUrl.openStream()){
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] buffer1 = new byte[1024];
            int read;
            while ((read = is1.read(buffer1)) != -1) {
                md.update(buffer1, 0, read);
            }
            byte[] localHash = md.digest();
            md.reset();
            byte[] buffer2 = new byte[1024];
            while ((read = is2.read(buffer2)) != -1) {
                md.update(buffer2, 0, read);
            }
            byte[] remoteHash = md.digest();
            return Arrays.equals(localHash, remoteHash);
        }
    }
}
