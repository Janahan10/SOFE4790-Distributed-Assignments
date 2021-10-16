package server;

import java.net.*;
import java.io.*;

public class Server {
    private Socket socket;
    private ServerSocket server;
    private BufferedReader input;
    private PrintWriter output;

    public Server(int port) {
        try {
            server = new ServerSocket(port);

            System.out.println("Server is running...");
            System.out.println("Waiting for client connection...");

            socket = server.accept();
            System.out.println("Client Connected");

            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            output = new PrintWriter(socket.getOutputStream(), true);

            String in, out;

            out = "YEP response from server";
            output.println(out);

            while((in = input.readLine()) != null) {
                if(in.equals("bye")) {
                    break;
                }
                out = "response from server changed in loop";
                output.println(out);

            }
            System.out.println("Ending Connection...");
            socket.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Server server = new Server(8008);
    }
}
