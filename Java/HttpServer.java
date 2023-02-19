import java.io.*;
import java.net.*;

public class HttpServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        while (true) {
            Socket socket = server.accept();
            Thread thread = new Thread(new RequestHandler(socket));
            thread.start();
        }
    }

    private static class RequestHandler implements Runnable {
        private Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = reader.readLine();
                String[] parts = line.split(" ");
                String method = parts[0];
                String path = parts[1];

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                if (method.equals("GET")) {
                    String response = "HTTP/1.1 200 OK\r\n\r\nHello, World!";
                    writer.println(response);
                } else {
                    String response = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";
                    writer.println(response);
                }

                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
