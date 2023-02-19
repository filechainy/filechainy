import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class FileConverter {

  public static final int CHUNK_SIZE = 8 * 1024; // 8 KB

  public static void main(String[] args) throws IOException {
    File directory = new File("files");
    for (File file : directory.listFiles()) {
      if (file.isFile()) {
        byte[] fileData = readFile(file);
        String encodedFile = encodeFile(fileData);
        splitEncodedFile(encodedFile, file.getName());
      }
    }
  }

  private static byte[] readFile(File file) throws IOException {
    try (FileInputStream inputStream = new FileInputStream(file)) {
      byte[] fileData = new byte[(int) file.length()];
      inputStream.read(fileData);
      return fileData;
    }
  }

  private static String encodeFile(byte[] fileData) {
    return Base64.getEncoder().encodeToString(fileData);
  }

  private static void splitEncodedFile(String encodedFile, String fileName) throws IOException {
    int chunkCount = encodedFile.length() / CHUNK_SIZE + (encodedFile.length() % CHUNK_SIZE == 0 ? 0 : 1);
    for (int i = 0; i < chunkCount; i++) {
      int startIndex = i * CHUNK_SIZE;
      int endIndex = Math.min(startIndex + CHUNK_SIZE, encodedFile.length());
      String chunk = encodedFile.substring(startIndex, endIndex);
      writeChunkToFile(chunk, fileName, i);
    }
  }

  private static void writeChunkToFile(String chunk, String fileName, int chunkIndex) throws IOException {
    try (FileOutputStream outputStream = new FileOutputStream(String.format("%s_%d", fileName, chunkIndex))) {
      outputStream.write(chunk.getBytes());
    }
  }
}
