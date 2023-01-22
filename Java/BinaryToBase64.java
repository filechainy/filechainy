import java.io.*;
import java.security.MessageDigest;
import java.util.Base64;

public class BinaryToBase64 {
    public static void main(String[] args) throws Exception {
        // Read the binary file into a byte array
        File file = new File("D:/a/0.jpg");
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream inputStream = new FileInputStream(file);
        inputStream.read(fileContent);
        inputStream.close();

        // Convert the byte array to a base64 string
        String base64String = Base64.getEncoder().encodeToString(fileContent);

        // Generate a SHA-1 hash of the base64 string
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hash = digest.digest(base64String.getBytes("UTF-8"));
        String hashString = bytesToHex(hash);

        // Save the base64 string to a file with the SHA-1 hash as the file name
        FileOutputStream outputStream = new FileOutputStream(hashString + ".txt");
        outputStream.write(base64String.getBytes());
        outputStream.close();
    }

    // Converts a byte array to a hexadecimal string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) result.append(String.format("%02x", b));
        return result.toString();
    }
}
