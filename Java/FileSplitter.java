import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileSplitter {

    private static final int CHUNK_SIZE = 8; // chunk size in bytes

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        // Check if the file path is provided as an argument
/*/
        if (args.length != 1) {
            System.out.println("Usage: FileSplitter <file_path>");
            return;
        }
/*/
        // Get the file to be split
        File file = new File("D:/a/a.txt");

        // Check if the file exists
        if (!file.exists()) {
            System.out.println("Error: File does not exist");
            return;
        }

        // Open the file for reading
        InputStream inputStream = new FileInputStream(file);

        // Initialize the SHA-1 message digest
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

        // Read the file in chunks
        byte[] chunk = new byte[CHUNK_SIZE];
        int chunkCount = 0;
        int read;
        while ((read = inputStream.read(chunk)) != -1) {
            // Calculate the SHA-1 hash of the chunk
            sha1.update(chunk);
            byte[] hash = sha1.digest();

            // Convert the hash to a hex string
            String hashString = bytesToHex(hash);

            // Create a new file with the hash as the file name
            FileOutputStream outputStream = new FileOutputStream("D:/a/" + hashString);

            // Write the chunk to the file
            outputStream.write(chunk, 0, read);

            // Close the file
            outputStream.close();

            // Increment the chunk count
            chunkCount++;
        }

        // Close the input stream
        inputStream.close();

        System.out.println("Split the file into " + chunkCount + " chunks");
    }

    // Converts a byte array to a hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
