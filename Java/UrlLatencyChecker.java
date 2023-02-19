import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UrlLatencyChecker extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton startButton;
    private JTextArea resultArea;
    private boolean isRunning;

    public UrlLatencyChecker() {
        super("URL Latency Checker");
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        JPanel controlPanel = new JPanel();
        controlPanel.add(startButton);
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == startButton) {
            isRunning = !isRunning;
            startButton.setText(isRunning ? "Stop" : "Start");
            if (isRunning) {
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        List<String> urls = readUrlsFromFile("urls.txt");
                        Iterator<String> iterator = urls.iterator();
                        while (isRunning) {
                            try {
                                while (iterator.hasNext()) {
                                    String url = iterator.next();
                                    long startTime = System.currentTimeMillis();
                                    URL u = new URL(url);
                                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                                    connection.setRequestMethod("HEAD");
                                    connection.connect();
                                    int responseCode = connection.getResponseCode();
                                    long endTime = System.currentTimeMillis();
                                    int latency = (int) (endTime - startTime);
                                    if (responseCode == HttpURLConnection.HTTP_OK) {
                                        displayResult(url + ": " + latency + " ms\n");
                                    } else {
                                        displayResult(url + " is offline\n");
                                        iterator.remove();
                                        writeUrlsToFile(urls, "urls.txt");
                                    }
                                }
                                iterator = urls.iterator();
                                Thread.sleep(10000);
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        }
    }

    private List<String> readUrlsFromFile(String fileName) {
        List<String> urls = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    urls.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }

    private void writeUrlsToFile(List<String> urls, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String url : urls) {
                writer.write(url + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayResult(String result) {
        resultArea.append(result);
        resultArea.setCaretPosition(resultArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        new UrlLatencyChecker();
    }
}
