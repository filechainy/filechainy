import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class PictureViewerFull {

  private JFrame frame;
  private JPanel panel;
  private JLabel label;
  private JScrollPane scrollPane;
  private static final int IMAGE_WIDTH = 300;
  private static final int IMAGE_HEIGHT = 300;

  public PictureViewerFull() {

    frame = new JFrame();   
    panel = new JPanel();
    label = new JLabel();
    scrollPane = new JScrollPane(panel,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
   
    panel.add(label);
    panel.setPreferredSize(new Dimension(1200, 100000));

    frame.setSize(1200, 600);
    frame.setTitle("Filechainy");
    frame.add(scrollPane);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //frame.setResizable(false);
    //frame.pack();
    frame.setVisible(true);    

    // Load the images in the directory into the GUI
    loadImages();
  }

  private void loadImages() {
    try (BufferedReader reader = new BufferedReader(new FileReader("pics.txt"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        URL url = new URL(line);
        ImageIcon icon = new ImageIcon(url);
        Image image = icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            try {
              //Desktop.getDesktop().browse(new URI(url.toString()));
              downloadImage(url);
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          }
        });
        // Update the panel when a picture is loaded
        panel.add(imageLabel);
        frame.pack();
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    //frame.pack();
  }

  private void downloadImage(URL url) {

    String urlFileName = url.toString();
    String[] parts = urlFileName.split("/");
    String filename = parts[parts.length - 1];  

    File picsDirectory = new File("pics");
    if (!picsDirectory.exists()) {
      picsDirectory.mkdir();
    }
    try (InputStream in = url.openStream();
         OutputStream out = new FileOutputStream(new File(picsDirectory, filename))) {
      byte[] buffer = new byte[1024];
      int len;
      while ((len = in.read(buffer)) != -1) {
        out.write(buffer, 0, len);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new PictureViewerFull();
  }
}
