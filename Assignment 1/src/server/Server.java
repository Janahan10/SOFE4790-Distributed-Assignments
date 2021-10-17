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

            String userInput;
            String serverResponse;

            serverResponse = "Hello! Welcome to Quadratic Equation Solver!" +
                    "Please enter a, b, and c, coefficients separated by a comma(',')";
            output.println(serverResponse);

            while((userInput = input.readLine()) != null) {
                if(userInput.equals("bye")) {
                    break;
                }

                int[] coefficients = parseInput(userInput);

                serverResponse = calculateRoots(coefficients);
                output.println(serverResponse + "If you want to try another problem you can enter another set of coefficients"
                        + "Or say 'bye' to terminate the session");
            }

            output.println("Goodbye. :) Ending Connection...");
            System.out.println("Ending Connection...");
            socket.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[] parseInput(String userInput) {
        String[] values = userInput.split(",");
        int[] result = new int[3];

        for (int i = 0; i < values.length; i++) {
            result[i] = Integer.parseInt(values[i]);
        }

        return result;
    }

    public String calculateRoots(int[] coefficients) {
        String result;

        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        // calculate determinant of roots
        double determinant = Math.pow(b, 2) - 4 * a * c;

        // based on determinant find the roots of the equation
        if (determinant < 0) {
            result = "There are no roots because the determinant is less than 0.";
        } else if(determinant == 0) {
            double root = Math.round((-b / (2 * a)) * 100.0) / 100.0;
            result = "Since determinant is 0, there is only 1 root. The root is " + root + ".";
        } else {
            double root1 = Math.round((-b + Math.sqrt(determinant)) * 100.0) / 100.0;
            double root2 = Math.round((-b - Math.sqrt(determinant)) * 100.0) / 100.0;

            result = "There are 2 roots. There 2 roots are " + root1 + " and " + root2 + ".";
        }

        return result;
    }

    public static void main(String args[]) {
        Server server = new Server(8008);
    }
}
