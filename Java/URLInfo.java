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

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> urls = Files.readAllLines(Paths.get("urls.txt"));
        List<String> files = getFilesInDirectory("files");
        List<URLInfo> urlInfos = new ArrayList<>();

        for (String url : urls) {
            boolean exists = true;
            for (String file : files) {
                exists &= fileExistsAtURL(url + "/" + file);
            }
            if (exists) {
                URLInfo urlInfo = new URLInfo(url, getLatency(url));
                urlInfos.add(urlInfo);
            }
        }

        Collections.sort(urlInfos);
        for (URLInfo urlInfo : urlInfos) {
            System.out.println(urlInfo.getUrl());
        }
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

class URLInfo implements Comparable<URLInfo> {
    private String url;
    private long latency;

    public URLInfo(String url, long latency) {
        this.url = url;
        this.latency = latency;
    }

    public String getUrl() {
        return url;
    }

    public long getLatency() {
        return latency;
    }

    @Override
    public int compareTo(URLInfo o) {
        return Long.compare(latency, o.latency);
    }
}
