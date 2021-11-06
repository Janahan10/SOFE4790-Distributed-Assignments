package client;

import java.net.*;
import java.io.*;
import java.nio.Buffer;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Client(String address, int port){
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to Server");

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while((fromServer = input.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("Bye!")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if(fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    output.println(fromUser);
                }
            }
        } catch (UnknownHostException u) {
            u.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 8008);
    }
}
