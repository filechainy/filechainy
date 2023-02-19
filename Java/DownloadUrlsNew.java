import java.io.*;
import java.net.*;

public class DownloadUrlsNew {
  public static void main(String[] args) throws Exception {
    // Read the URLs from the text file
    File file = new File("urls.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));
    String urlStr;
    while ((urlStr = br.readLine()) != null) {
      URL url = new URL(urlStr);
      String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
      // Open a connection to the URL and download the content
      InputStream in = url.openStream();
      FileOutputStream fos = new FileOutputStream("files/" + fileName);
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0) {
        fos.write(buf, 0, len);
      }
      in.close();
      fos.close();
    }
    br.close();
  }
}
