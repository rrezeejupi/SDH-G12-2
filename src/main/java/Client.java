import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Client:");

            // Receive prompt from server
            System.out.println(in.readLine()); // "Awaiting credentials..."

            // Send credentials
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            out.println(username);

            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            out.println(password);

            // Read response
            String response = in.readLine();
            if (response.startsWith("SUCCESS:")) {
                String token = response.substring("SUCCESS:".length());
                System.out.println("Logged in. JWT token is: " + token);

                while (true) {
                    System.out.print("Enter command ('request_data' or 'logout'): ");
                    String command = scanner.nextLine();

                    // Send token and command to server
                    out.println(token);
                    out.println(command);

                    String serverResponse = in.readLine();
                    if (serverResponse.equals("LOGOUT")) {
                        System.out.println("Logging out...");
                        break;
                    } else if (serverResponse.equals("INVALID_TOKEN")) {
                        System.out.println("Session expired or invalid token.");
                        break;
                    } else if (serverResponse.startsWith("DATA:")) {
                        System.out.println("Protected data received: " + serverResponse.substring("DATA:".length()));
                    } else if (serverResponse.equals("INVALID_COMMAND")) {
                        System.out.println("Invalid command.");
                    } else {
                        System.out.println("Server: " + serverResponse);
                    }
                }
            } else {
                System.out.println("Login failed: " + response);
            }

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
