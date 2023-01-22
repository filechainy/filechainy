import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileDropMenu {
    private JFrame frame;
    private JButton button;
    private JFileChooser fileChooser;

    public FileDropMenu() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        button = new JButton("Select file");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        copyFile(selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        panel.add(button);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

private void copyFile(File sourceFile) throws IOException {
    File targetDir = new File("files");
    if (!targetDir.exists()) {
        targetDir.mkdir();
    }

    File targetFile = new File(targetDir, sourceFile.getName());
    FileChannel source = null;
    FileChannel destination = null;
    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(targetFile).getChannel();
        destination.transferFrom(source, 0, source.size());
        JOptionPane.showMessageDialog(frame, "File copied to " + targetFile.getAbsolutePath());

        // Convert the file to base64 and split into chunks of 8000 bytes
        byte[] fileBytes = new byte[(int) targetFile.length()];
        FileInputStream inputStream = new FileInputStream(targetFile);
        inputStream.read(fileBytes);
        inputStream.close();

        String encodedFile = Base64.getEncoder().encodeToString(fileBytes);
        int chunkSize = 8000;
        for (int i = 0; i < encodedFile.length(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, encodedFile.length());
            String chunk = encodedFile.substring(i, endIndex);

            // Save chunk as a new file with SHA-1 name
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                byte[] hash = digest.digest(chunk.getBytes());

                StringBuilder hexString = new StringBuilder();
                for (int j = 0; j < hash.length; j++) {
                   String hex = Integer.toHexString(0xff & hash[j]);
                   if(hex.length() == 1) hexString.append('0');
                   hexString.append(hex);
                }

                File newFile = new File(targetDir, hexString.toString());
                FileOutputStream outputStream = new FileOutputStream(newFile);
                outputStream.write(chunk.getBytes());
                outputStream.close();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
    } finally {
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }
} 

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        FileDropMenu menu = new FileDropMenu();
        menu.show();
    }
}
