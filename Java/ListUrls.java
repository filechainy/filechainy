import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListUrls {
  public static void main(String[] args) throws IOException {
    File urlDirectory = new File("urls");
    File[] urlFiles = urlDirectory.listFiles();

    for (File urlFile : urlFiles) {
      Scanner fileScanner = new Scanner(urlFile);
      String url = fileScanner.nextLine();
      fileScanner.close();

      URL website = new URL(url);
      Scanner input = new Scanner(website.openStream());
      StringBuilder content = new StringBuilder();
      while (input.hasNextLine()) {
        content.append(input.nextLine());
      }
      input.close();

      Pattern p = Pattern.compile("href=\"(.*?)\"");
      Matcher m = p.matcher(content.toString());
      while (m.find()) {
        String newUrl = m.group(1);
        int hashCode = newUrl.hashCode();
        FileWriter fw = new FileWriter("urls/" + hashCode + ".txt");
        fw.write(newUrl);
        fw.close();
      }
    }
  }
}
