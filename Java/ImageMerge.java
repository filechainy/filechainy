import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageMerge {

  public static void main(String[] args) throws IOException {
    // Specify the directory where the images are located
    File directory = new File("pics");
    File[] images = directory.listFiles();
    
    // Create a list to store the cropped images
    ArrayList<BufferedImage> croppedImages = new ArrayList<>();
    
    // Crop each image
    for (File image : images) {
      BufferedImage originalImage = ImageIO.read(image);
      int width = originalImage.getWidth();
      int height = originalImage.getHeight();
      Random random = new Random();
      int x = random.nextInt(width - 200);
      int y = random.nextInt(height - 200);
      BufferedImage croppedImage = originalImage.getSubimage(x, y, 200, 200);
      croppedImages.add(croppedImage);
    }
    
    // Create a new image to store the merged images
    BufferedImage mergedImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = mergedImage.createGraphics();
    
    // Merge the cropped images
    int x = 0;
    int y = 0;
    for (BufferedImage croppedImage : croppedImages) {
      g.drawImage(croppedImage, x, y, null);
      x += 200;
      if (x >= 800) {
        x = 0;
        y += 200;
      }
    }
    
    // Save the merged image
    ImageIO.write(mergedImage, "JPEG", new File("merged.jpeg"));
  }
}
