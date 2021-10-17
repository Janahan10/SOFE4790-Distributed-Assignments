package server;

import java.net.*;
import java.io.*;

public class Server {
    // initialization of server components
    private Socket socket;
    private ServerSocket server;
    private BufferedReader input;
    private PrintWriter output;

    public Server(int port) {
        try {
            // creating server socket for connection
            server = new ServerSocket(port);

            System.out.println("Server is running...");
            System.out.println("Waiting for client connection...");

            // connect client to server
            socket = server.accept();
            System.out.println("Client Connected");

            // create input and output streams for server
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            output = new PrintWriter(socket.getOutputStream(), true);

            // user input and server response initialization
            String userInput;
            String serverResponse;

            // request processing time (NOVEL FEATURE)
            double duration = -1.0;

            // string explaining time taken to finish task
            String time = "";

            // initial server prompt to client
            serverResponse = "Hello! Welcome to Quadratic Equation Solver!" +
                    "Please enter a, b, and c, coefficients separated by a comma(',')";
            output.println(serverResponse);

            while((userInput = input.readLine()) != null) {
                // connection termination check
                if(userInput.equals("bye")) {
                    break;
                }

                // start time of processing request
                double startTime = System.currentTimeMillis();

                // breaks users input into data
                int[] coefficients = parseInput(userInput);

                serverResponse = calculateRoots(coefficients);

                // end time for processing request
                double endTime = System.currentTimeMillis();

                // find out how long the current request compares to the last one
                if (duration == -1.0) {
                    duration = endTime - startTime;
                } else if (duration > endTime - startTime) {
                    time = "(This request took less time than the last one!)";
                } else if (duration == endTime - startTime) {
                    time = "(This request took equal amount of time as the last one!)";
                } else {
                    time = "(This request took longer than the last one!)";
                }

                // output to client on result
                output.println(serverResponse + "If you want to try another problem you can enter another set of coefficients"
                        + "Or say 'bye' to terminate the session. " + time);
            }

            // end of connection details
            output.println("Goodbye. :) Ending Connection...");
            System.out.println("Ending Connection...");
            socket.close();
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method for parsing a comma delimited string for further use
    private int[] parseInput(String userInput) {
        String[] values = userInput.split(",");
        int[] result = new int[3];

        for (int i = 0; i < values.length; i++) {
            result[i] = Integer.parseInt(values[i]);
        }

        return result;
    }

    // method for calculating quadratic roots (SERVICE)
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
