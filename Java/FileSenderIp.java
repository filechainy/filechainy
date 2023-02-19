import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class FileSenderIp {

  public static void main(String[] args) throws IOException {
    String dirPath = "files"; // directory containing the files to be sent
    String targetIp = "192.168.1.100"; // IP address to send the files to
    int targetPort = 8080; // port on the target IP to send the files to

    File dir = new File(dirPath);
    File[] files = dir.listFiles();

    for (File file : files) {
      try (Socket socket = new Socket(targetIp, targetPort)) {
        OutputStream outputStream = socket.getOutputStream();

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
          byte[] buffer = new byte[4096];
          int bytesRead;
          while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
          }
        }
      }
    }
  }
}