package net.codejava.graphic;
 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import javax.imageio.ImageIO;

class ImgOrganize{

    public static void main(String args[]){

        for (int count = 0; count < 100; count++){

        int W = 0;
        int H = 0;
        int E = 0;       

        try{

        File inputFile = new File("D:/xyz/a" + count +".jpg");
        BufferedImage inputImage = ImageIO.read(inputFile);

       	int y = inputImage.getHeight();
	int x = inputImage.getWidth();

        String folder = "";
        int temp = 1;

        if (x > y){folder= "W";}
	if (x < y){folder = "H";}
        if (x == y){folder = "E"; E++;}

        Path yourFile = Paths.get("D:/xyz/a" + count +".jpg");
        Files.move(yourFile, yourFile.resolveSibling("D:/xyz/" + "/" + folder + "/" + count + "t.jpg"));

        } catch (Exception e){}

        }

    }
}
