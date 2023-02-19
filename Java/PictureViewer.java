import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class PictureViewer {

  private JFrame frame;
  private JPanel panel;
  private JLabel label;
  private static final int IMAGE_WIDTH = 200;
  private static final int IMAGE_HEIGHT = 200;

  public PictureViewer() {
    frame = new JFrame();
    panel = new JPanel();
    label = new JLabel();

    panel.add(label);
    frame.add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
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
              Desktop.getDesktop().browse(new URI(url.toString()));
            } catch (IOException | URISyntaxException ex) {
              ex.printStackTrace();
            }
          }
        });
        panel.add(imageLabel);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    frame.pack();
  }

  public static void main(String[] args) {
    new PictureViewer();
  }
}
