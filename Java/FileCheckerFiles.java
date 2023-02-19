import java.io.*;
import java.util.*;
import java.net.*;

public class FileCheckerFiles {
    public static void main(String[] args) {
        String urlsFile = "urls.txt";
        String resultsFile = "results.txt";

        Map<String, Integer> results = new HashMap<>();
        List<String> urls = readUrlsFromFile(urlsFile);
        File[] files = getFilesFromDirectory("files");

        // Check if the files exist in each URL
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

        // Sort the results in descending order by value
        List<Map.Entry<String, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // Write the results to the 'results.txt' file
        writeResultsToFile(resultsFile, sortedResults);
    }

    private static List<String> readUrlsFromFile(String fileName) {
        List<String> urls = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String url;
            while ((url = br.readLine()) != null) {
                urls.add(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return urls;
    }

    private static File[] getFilesFromDirectory(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }

    private static boolean checkFileExistsInUrl(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }

    private static void writeResultsToFile(String fileName, List<Map.Entry<String, Integer>> results) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Integer> result : results) {
                bw.write(result.getKey() + ": " + result.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
