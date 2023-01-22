import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class Filechainy implements ActionListener {

//Built in JDK 12.0.2
 
    JFrame main = new JFrame();
    ImageIcon logo = new ImageIcon("logo_1.png");

    JLabel logoFrame = new JLabel(logo);
    JLabel labelInfo = new JLabel("<html><h1>Filechain microdatabase</h1> Files are enconded in base64 and splitted in chunks that receive a SHA1 hash name<br> This design where the files are splitted in small blocks instead be one big file and<br> each small piece receive a hash as name provides a simple but eficient mechanism<br> for transfer files and check if each file of each host is really valid (has a valid hash)</html>");
    JLabel labelShowInput = new JLabel();
    JLabel labelSpace = new JLabel("     "); 
    JLabel labelFinished = new JLabel();

    String strPayAddr = "Payment Address"; 
    String strFinished = "Finished!";
    String strSent = "Sent!";
      
    String wallet = "";
    String user = "";
    int fileCounter = 0;
    
    Filechainy() {

        final int windowHeight = 600;        
        final int windowWidth = 800;
        final int buttonHeight = 30;
        final int buttonWidth = 100;
        final int grayHexadecimalColor = 0xDDDDDD;
        final int colorGrayLight = 0xDDDDDD;

        JButton buttonStart= new JButton("Start");
        JButton buttonExit = new JButton("Exit");
        JButton buttonDownload = new JButton("Download");
        JButton buttonSend = new JButton("Send");

        JButton buttonPT = new JButton("PT |");
        buttonPT.setBorderPainted(false);
        buttonPT.setOpaque(false);
        buttonPT.setBackground(Color.WHITE);

        JButton buttonENG = new JButton("ENG");
        buttonENG.setBorderPainted(false);
        buttonENG.setOpaque(false);
        buttonENG.setBackground(Color.WHITE);

        JButton buttonTwitter = new JButton("Twitter");
        buttonTwitter.setBackground(new Color(colorGrayLight));
        buttonTwitter.setBorderPainted(false);
        buttonTwitter.setFocusPainted(false);

        JButton buttonInsta = new JButton("Instagram");
        buttonInsta.setBorderPainted(false);
        buttonInsta.setFocusPainted(false);
        buttonInsta.setBackground(new Color(colorGrayLight));

        JButton buttonReddit = new JButton("Reddit");
        buttonReddit.setBorderPainted(false);
        buttonReddit.setFocusPainted(false);
        buttonReddit.setBackground(new Color(colorGrayLight));

        JButton buttonTelegram = new JButton("Telegram");
        buttonTelegram.setBorderPainted(false);
        buttonTelegram.setFocusPainted(false);
        buttonTelegram.setBackground(new Color(colorGrayLight));

        JButton buttonPIX = new JButton("PIX");
        buttonPIX.setBorderPainted(false);
        buttonPIX.setFocusPainted(false);
        buttonPIX.setBackground(new Color(colorGrayLight));

        JButton buttonPayPal = new JButton("PayPal");
        buttonPayPal.setBorderPainted(false);
        buttonPayPal.setFocusPainted(false);
        buttonPayPal.setBackground(new Color(colorGrayLight));

        JButton buttonGithub = new JButton("Github");
        buttonGithub.setBorderPainted(false);
        buttonGithub.setFocusPainted(false);
        buttonGithub.setBackground(new Color(colorGrayLight));

        buttonStart.addActionListener(this);
        buttonPT.addActionListener(this);
        buttonENG.addActionListener(this);      
        buttonPayPal.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonDownload.addActionListener(this);
        buttonSend.addActionListener(this);
        buttonTwitter.addActionListener(this);
        buttonInsta.addActionListener(this);
        buttonReddit.addActionListener(this);
        buttonGithub.addActionListener(this);

        buttonStart.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonExit.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonDownload.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonSend.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(new Color(0x00FF55));
        bottom.setBounds(0, 500, 800, 200);    

        bottom.add(labelFinished);
        bottom.add(buttonStart);
        bottom.add(buttonSend);
        bottom.add(buttonDownload);    
        bottom.add(buttonExit);         
        bottom.add(labelSpace);    

        JPanel left = new JPanel();
        left.setBackground(new Color(0xFFFFFF));
        left.setBounds(0, 0, windowHeight, windowWidth); 

        left.add(buttonPT); 
        left.add(buttonENG); 
        left.add(logoFrame);  
        left.add(labelInfo); 

        JPanel right = new JPanel(new FlowLayout(FlowLayout.LEFT));
        right.setBackground(new Color(0xEEEEEE));
        right.setBounds(600, 0, 200, 600); 

        right.add(buttonTwitter); 
        right.add(buttonInsta); 
        right.add(buttonReddit); 
        right.add(buttonTelegram); 
        right.add(buttonPIX); 
        right.add(buttonPayPal); 
        right.add(buttonReddit); 
        right.add(buttonGithub); 
 
        main.setVisible(true);
        main.setSize(windowWidth, windowHeight);
        main.setTitle("Filechainy 1.0");
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setResizable(false);
        main.setLayout(null);
        main.add(bottom);
        main.add(left);
        main.add(right);
        
        main.setIconImage(logo.getImage());      
    }

    public void linkBrowser(String link){
          
            try {
            
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(URI.create(link));
            
            } catch (IOException e) {
	        e.printStackTrace();
	    }

    }
  
    public void actionPerformed(ActionEvent event) {         

        String buttonName = (((JButton) event.getSource()).getText());

        if (buttonName.equals("Twitter")) { 
            linkBrowser("https://twitter.com/filechainy");
        } 

        if (buttonName.equals("Instagram")) { 
            linkBrowser("https://www.instagram.com/filechainy/");
        } 

        if (buttonName.equals("Telegram")) { 
            linkBrowser("http://t.me/filechainy");
        } 

        if (buttonName.equals("Discord")) { 
            linkBrowser("https://discord.gg/XqHb9veUvb");
        } 

        if (buttonName.equals("Github")) { 
            linkBrowser("https://github.com/filechainy/filechainy");
        } 

        if (buttonName.equals("PIX")) { 
            linkBrowser("filechainy@gmail.com");
        } 

        if (buttonName.equals("Reddit")) { 
            linkBrowser("https://www.reddit.com/user/filechainy");
        } 

        if (buttonName.equals("PayPal")) { 
            linkBrowser("https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&business=aerae4%40gmail%2ecom&lc=US&item_name=Registration%20fee&item_number=2&amount=15%2e00&currency_code=USD&button_subtype=services&no_note=0&bn=PP%2dBuyNowBF%3abtn_buynowCC_LG%2egif%3aNonHostedGuest");
        } 

        if (buttonName.equals("PT |")) {              
            
            labelInfo.setText("<html><h1>Filechain microbanco-de-dados</h1> Cada arquivo é convertido em base64 e dividido em pedaços com um hash <b>SHA1</b><br> Esse design onde os arquivos são repartidos em pequenos pedaços <br> e cada um deles receber um hash possibilita uma simples e eficiente maneira<br> de transferir arquivos e checar a validade deles (se possui um hash válido)</html>");
            strPayAddr = "Endereço de pagamento";
            strFinished = "Terminado!"; 
            strSent = "Enviado!"; 
        } 

        if (buttonName.equals("ENG")) {              
            
            labelInfo.setText("<html><h1>Filechain microdatabase</h1> Files are enconded in base64 and splitted in chunks that receive a SHA1 hash name.<br> This design where the files are splitted in small blocks instead be one big file and<br> each small piece receive a hash as name provides a simple but eficient mechanism<br> for transfer files and check if each file of each host is really valid (has a valid hash)</html>");
            strPayAddr =  "Payment Address";
            strFinished = "Finished!"; 
            strSent = "Sent!"; 
        } 

        if (buttonName.equals("Exit")) {              
             System.out.print("Exit");
             System.exit(0);
         } 
      
        if (buttonName.equals("Download") || buttonName.equals("Start")) {
            /* Download the files of the sites in urls.txt

            */             

            // Create the folder "hash" for save the files 
            String fileFolder = "hash";

            File theDir = new File(fileFolder);
                     
            if (!theDir.exists()){
                theDir.mkdirs();
            }    

            try  
            {  
                // Read the list of URLS in "urls.txt"     

                File file = new File("urls.txt");    
                FileReader fr = new FileReader(file);  
                BufferedReader br = new BufferedReader(fr);  
                StringBuffer sb = new StringBuffer();   

                String line;  
                String urlInsert;
                String URLhash;

                String prevStatus = "<html>";         

                while((line=br.readLine())!=null)  
                {
                    try  
                    {                

                         System.out.println("Downloading");           

                         File fileFullPath;
                         String urlDir = "";
                         String urlContents = "";
                         String hashURL = "";
                         String filename;
                         URL url;                       
                        
                         int blankEntries = 0;
                         String line2 = "";

                         fileCounter = 0;

                         String firstChar = "";

                         // Loop each URL with "id" Get variable
                         while (true){                                 
   
                            urlDir = line + "?id=" +  fileCounter;
                            
                            StringBuilder content = new StringBuilder();  

                            url = new URL(urlDir); // creating a url object  
                            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object  
    
                            // wrapping the urlconnection in a bufferedreader  
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));   
                            // reading from the urlconnection using the bufferedreader  
    
                            while ((line2 = bufferedReader.readLine()) != null)  
                            {  
                                content.append(line2);  
                            }  
       
                            bufferedReader.close();  

                            hashURL = content.toString(); 

                            firstChar = hashURL.substring(0,1);

                            if(!firstChar.equals("h")){
 
                                // Discard unwanted characters in beginning of text file
                                hashURL = hashURL.substring(3);    

                            }
                
                            filename = hashURL.substring(hashURL.lastIndexOf("/") + 1);   

                            hashURL = hashURL.replace(filename, URLEncoder.encode(filename, "UTF-8"));
                            //hashURL = hashURL.replace(" ", "%");                               

                            if(hashURL.equals("")){break;}

                            if(blankEntries > 1){System.out.println("Reached");blankEntries = 0; break;}

                            fileFullPath = new File(fileFolder + "/" +filename);
          
                            //If the file exists jump to the next
                            if (fileFullPath.exists()){System.out.println(filename + " Already exists"); fileCounter++; continue;}  

                            System.out.println(hashURL);
                
                            try (InputStream in = URI.create(hashURL).toURL().openStream()) {

                                Files.copy(in, Paths.get(fileFolder + "/" + filename));                    

                                System.out.println(urlDir);                                
                
                            } catch (IOException f) {
		   	        f.printStackTrace();

                            }

                            fileCounter++; 
                        }                              

                    } catch (IOException f) {
                        f.printStackTrace();
                    } 

                //fr.close();
                      
                }
                fr.close();
               
            } catch (IOException f) {
                f.printStackTrace();
            }

        labelFinished.setText(strFinished);
                
        } 

        if (buttonName.equals("Send") || buttonName.equals("Start")) {

        /* 

        Read a list of URLs and send to each URL the files from "hash" folder (must be in base64)
        Each url need be separated by line feed in "urls.txt".   

        */

            user = (String)JOptionPane.showInputDialog(main, strPayAddr + ":", strPayAddr,JOptionPane.PLAIN_MESSAGE,logo, null,"");
            System.out.print(user); 

            labelShowInput.setText("<html> Welcome" + wallet + "!");  

            System.out.println("Send");

            if (user.equals("")){JOptionPane.showMessageDialog(main, "Your address is blank or empty.", "Empty user",  JOptionPane.WARNING_MESSAGE);} 

                try  
                {  
                    // Read the list of URLS             
                    File file = new File("urls.txt");    
                    FileReader fr = new FileReader(file);  
                    BufferedReader br = new BufferedReader(fr);  
                    StringBuffer sb = new StringBuffer();   

                    String line;  
                    String urlInsert;
                    
                    // Folder with chunks of files already converted to base64
                    File folder = new File("hash");
                    File[] files = folder.listFiles();  

                    String prevStatus = "<html>";         

                    while((line=br.readLine())!=null)  
                    {         
                        for (int i = 0; i < files.length; i++) {

                            if (files[i].isFile()) {

                                try  
                                {  
                                    // Read the contents of each base64 file                        

                                    FileReader fr2 = new FileReader(files[i]);   
                                    BufferedReader br2 = new BufferedReader(fr2);  
                                    StringBuffer sb2 = new StringBuffer();  

                                    String line2 = "";
                                    line2 = br2.readLine();
                                    String urlInsert2 = "";
                                                             
                                    while(line2!=null)    
                                    {              
                                        sb2.append(line2);
                                        sb2.append(System.lineSeparator());
                                        line2 = br2.readLine();            
                                    }

                                    String contents = null;
                                    contents = sb2.toString();

                                    String filenameURL = files[i].getName();

                                    filenameURL = URLEncoder.encode(filenameURL, "UTF-8");

                                   // URLEncoder for treat combinations that can broken the request

                                    urlInsert2 = line + "?hash=" + filenameURL + "&contents=" + URLEncoder.encode(contents, "UTF-8") + "&user=" + user;

                                    try (InputStream in = URI.create(urlInsert2).toURL().openStream()) {
                                    } catch (IOException f) {
                                        f.printStackTrace();
                                    }

                                    System.out.println(files[i].getName() + " sent to:" + line);

                                    prevStatus = prevStatus + "<br>" + files[i].getName() + " to" + line;
                                    labelShowInput.setText(prevStatus);                                               

                                    fr2.close();   

                                } catch (IOException f) {
                                
                                f.printStackTrace();
                            } 
                                                   
                        }
                 
                    }

                }

                fr.close();  

            } catch (IOException f) {
                f.printStackTrace();
            }            
        
            labelFinished.setText(strSent);     
        } 
    }

    public static void main(String args[]){

        //Use the GUI 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Filechainy();
            }
        }
    );    
    }
}