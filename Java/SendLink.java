import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SendLink {
  public static void main(String[] args) {
    ArrayList<String> fruits = new ArrayList<String>();
    File fruitDirectory = new File("fruits");
    for (File fruitFile : fruitDirectory.listFiles()) {
      try {
        Scanner scanner = new Scanner(fruitFile);
        while (scanner.hasNextLine()) {
          fruits.add(scanner.nextLine());
        }
        scanner.close();
      } catch (FileNotFoundException e) {
        System.err.println(e);
      }
    }

    ArrayList<String> urls = new ArrayList<String>();
    try {
      Scanner scanner = new Scanner(new File("urls.txt"));
      while (scanner.hasNextLine()) {
        urls.add(scanner.nextLine());
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println(e);
    }

    for (String fruit : fruits) {
      for (String url : urls) {
        try {
          URL completeURL = new URL(url + fruit);
          Scanner scanner = new Scanner(completeURL.openStream());
          while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
          }
          scanner.close();
        } catch (IOException e) {
          System.err.println(e);
        }
      }
    }
  }
}
