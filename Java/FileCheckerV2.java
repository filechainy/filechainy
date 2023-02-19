import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.util.*;

public class FileCheckerV2 {
    public static void main(String[] args) throws Exception {
        // Read the URLs from the text file
        List<URL> urls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("urls.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                urls.add(new URL(line + "/hash/"));
            }
        }

        // Check if the files of the 'hash' directory and of the URL have the same hash code
        File hashFolder = new File("hash");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        for (File file : hashFolder.listFiles()) {
            Path hash = file.toPath();
            byte[] localFileHash = Files.readAllBytes(hash);
            for (URL url : urls) {
                URL fileUrl = new URL(url.toString() + file.getName());
                try {
                    URLConnection connection = fileUrl.openConnection();
                    InputStream in = connection.getInputStream();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) != -1) {
                        out.write(buffer, 0, length);
                    }
                    byte[] remoteFileHash = out.toByteArray();
                    in.close();

                    if (!Arrays.equals(localFileHash, remoteFileHash)) {
                        System.out.println(file.getName() + " has a different hash code in " + url);
                        Thread.sleep(5000);
                    } else {
                        System.out.println(file.getName() + " has the same hash code in " + url);
                        Thread.sleep(5000);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(file.getName() + " not found in " + url);
                }
            }
        }
    }
}
