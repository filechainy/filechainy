import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainUrl {

    public static void main(String[] args) throws IOException {
        List<String> urls = Files.readAllLines(Paths.get("urls.txt"));
        List<String> files = getFilesInDirectory("files");
        List<URLResult> urlResults = new ArrayList<>();

        for (String url : urls) {
            int commonFiles = 0;
            for (String file : files) {
                if (fileExistsAtURL(url + "hash/" + file)) {
                    commonFiles++;
                }
            }
            if (commonFiles > 0) {
                URLResult urlResult = new URLResult(url, getLatency(url), commonFiles);
                urlResults.add(urlResult);
            }
        }

        Collections.sort(urlResults);
        List<String> results = new ArrayList<>();
        for (URLResult urlResult : urlResults) {
            results.add(urlResult.getUrl());
        }
        Files.write(Paths.get("results.txt"), results);
    }

    private static List<String> getFilesInDirectory(String directory) throws IOException {
        List<String> files = new ArrayList<>();
        Files.list(Paths.get(directory)).forEach(path -> files.add(path.getFileName().toString()));
        return files;
    }

    private static boolean fileExistsAtURL(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }

    private static long getLatency(String url) {
        long start = System.currentTimeMillis();
        try {
            URL website = new URL(url);
            website.openConnection().getInputStream();
        } catch (IOException e) {
            return -1;
        }
        return System.currentTimeMillis() - start;
    }
}

class URLResult implements Comparable<URLResult> {
    private String url;
    private long latency;
    private int commonFiles;

    public URLResult(String url, long latency, int commonFiles) {
        this.url = url;
        this.latency = latency;
        this.commonFiles = commonFiles;
    }

    public String getUrl() {
        return url;
    }

    public long getLatency() {
        return latency;
    }

    public int getCommonFiles() {
        return commonFiles;
    }

   @Override
    public int compareTo(URLResult o) {
        int latencyCompare = Long.compare(latency, o.latency);
        if (latencyCompare != 0) {
            return latencyCompare;
        }
        return Integer.compare(o.commonFiles, commonFiles);
    }
}
