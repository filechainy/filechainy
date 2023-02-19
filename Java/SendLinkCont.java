import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class SendLinkCont {
  public static void main(String[] args) {
    ArrayList<String> fruits = new ArrayList<String>();
    File fruitDirectory = new File("fruits");
    for (File fruitFile : fruitDirectory.listFiles()) {
      try {
        Scanner scanner = new Scanner(fruitFile);
        StringBuilder fruitContents = new StringBuilder();
        while (scanner.hasNextLine()) {
          fruitContents.append(scanner.nextLine());
        }
        fruits.add(fruitContents.toString());
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
          HttpURLConnection connection = (HttpURLConnection) completeURL.openConnection();
          connection.setDoOutput(true);
          connection.setRequestMethod("GET");
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
          outputStreamWriter.write(fruit);
          outputStreamWriter.flush();
          Scanner scanner = new Scanner(connection.getInputStream());
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
