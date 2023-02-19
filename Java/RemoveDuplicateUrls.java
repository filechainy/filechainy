import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateUrls {
    public static void main(String[] args) {
        Set<String> uniqueUrls = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("urls.txt"))) {
            String url;
            while ((url = reader.readLine()) != null) {
                uniqueUrls.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("urls.txt")) {
            for (String url : uniqueUrls) {
                writer.write(url + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
