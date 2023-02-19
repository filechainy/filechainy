import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class FileDownloaderGui {

  public static void main(String[] args) throws Exception {
    JFrame frame = new JFrame("Download Progress");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JProgressBar progressBar = new JProgressBar();
    progressBar.setIndeterminate(false);
    progressBar.setStringPainted(true);
    frame.add(progressBar);
    frame.setSize(300, 100);
    frame.setVisible(true);

    String fileListPath = "files.txt"; // file containing the list of files to be downloaded
    String downloadPath = "downloads"; // directory to save the downloaded files in

    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileListPath)));
    try {
      String fileUrl;
      int i = 0;
      int numFiles = 0;
      while ((fileUrl = reader.readLine()) != null) {
        numFiles++;
      }
      reader.close();
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileListPath)));
      while ((fileUrl = reader.readLine()) != null) {
        URL website = new URL(fileUrl);
        String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());
             FileOutputStream fos = new FileOutputStream(downloadPath + File.separator + fileName)) {
          fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
          i++;
          progressBar.setValue(100 * i / numFiles);
        }
      }
    } finally {
      if (reader != null) {
        reader.close();
      }
    }
  }
}
