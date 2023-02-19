import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class TextSenderGUI extends JFrame implements ActionListener {
    
    private JTextField textField;
    private JButton sendButton;
    
    public TextSenderGUI() {
        super("Text Sender");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textField = new JTextField(20);
        add(textField);
        
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        add(sendButton);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            String text = textField.getText();
            try {
                File file = new File("urls.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String url;
                while ((url = reader.readLine()) != null) {
                    sendToURL(text, url);
                }
                reader.close();
                JOptionPane.showMessageDialog(this, "Text sent successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error sending text: " + ex.getMessage());
            }
        }
    }

    
private void sendToURL(String text, String urlString) throws Exception {
    String encodedText = URLEncoder.encode(text, "UTF-8");
    URL url = new URL(urlString + "?contents=" + encodedText);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");
    int responseCode = connection.getResponseCode();
    if (responseCode != 200) {
        throw new Exception("Failed to send text to " + urlString + ", HTTP error code: " + responseCode);
    }
}

    
    public static void main(String[] args) {
        new TextSenderGUI();
    }
}
