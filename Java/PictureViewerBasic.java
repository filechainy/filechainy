import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import javax.swing.*;

public class PictureViewerBasic {

  private JFrame frame;
  private JPanel panel;
  private JLabel label;
  private static final int IMAGE_WIDTH = 200;
  private static final int IMAGE_HEIGHT = 200;

  public PictureViewerBasic() {
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
    Path picsDirectory = Paths.get("pics");
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(picsDirectory)) {
      for (Path entry : stream) {
        ImageIcon icon = new ImageIcon(entry.toString());
        Image image = icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            try {
              Desktop.getDesktop().browse(new URI(entry.toUri().toString()));
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
    new PictureViewerBasic();
  }
}
