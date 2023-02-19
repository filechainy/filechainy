import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUrlChecker {
    public static void main(String[] args) {
        // List to store URLs from urls.txt
        List<String> urls = readUrlsFromFile("urls.txt");
        
        // Map to store the result of file existence in each URL
        Map<String, Integer> results = new HashMap<>();

        // Check if the files in the 'files' directory exist in each URL
        File[] files = new File("files").listFiles();
        for (File file : files) {
            for (String url : urls) {
                if (checkFileExistsInUrl(url + "/" + file.getName())) {
                    if (results.containsKey(file.getName())) {
                        results.put(file.getName(), results.get(file.getName()) + 1);
                    } else {
                        results.put(file.getName(), 1);
                    }
                }
            }
        }
        
        // Save the results to the 'results.txt' file
        writeResultsToFile(results);
    }

    // Read URLs from urls.txt file
    private static List<String> readUrlsFromFile(String fileName) {
        List<String> urls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String url;
            while ((url = br.readLine()) != null) {
                urls.add(url);
            }
        } catch (IOException e) {
            System.out.println("Error reading urls.txt file: " + e.getMessage());
        }
        return urls;
    }

    // Check if the file exists in the URL
    private static boolean checkFileExistsInUrl(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            return (connection.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            return false;
        }
    }

    // Write results to the results.txt file
    private static void writeResultsToFile(Map<String, Integer> results) {
        try (FileWriter writer = new FileWriter("results.txt")) {
            for (Map.Entry<String, Integer> result : results.entrySet()) {
                writer.write(result.getKey() + ": " + result.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing results to results.txt file: " + e.getMessage());
        }
    }
}
