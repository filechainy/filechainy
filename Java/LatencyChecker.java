import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LatencyChecker {

    public static void main(String[] args) throws InterruptedException {
        String fileName = "urls.txt"; // Replace with the path to your file
        List<URLInfo> urlInfos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String urlString;
            while ((urlString = br.readLine()) != null) {
                URLInfo urlInfo = new URLInfo(urlString);
                urlInfos.add(urlInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            for (URLInfo urlInfo : urlInfos) {
                urlInfo.updateLatency();
            }

            Collections.sort(urlInfos);

            for (URLInfo urlInfo : urlInfos) {
                System.out.println(urlInfo);
            }

            Thread.sleep(60 * 1000); // Sleep for one minute
        }
    }
}

class URLInfo implements Comparable<URLInfo> {
    private final URL url;
    private long latency;

    public URLInfo(String urlString) throws IOException {
        this.url = new URL(urlString);
        updateLatency();
    }

    public void updateLatency() {
        try {
            long startTime = System.currentTimeMillis();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                latency = System.currentTimeMillis() - startTime;
            } else {
                latency = -1;
            }
        } catch (IOException e) {
            latency = -1;
        }
    }

    public long getLatency() {
        return latency;
    }

    @Override
    public int compareTo(URLInfo o) {
        return Long.compare(o.getLatency(), this.getLatency());
    }

    @Override
    public String toString() {
        return url + " : " + latency + " ms";
    }
}
