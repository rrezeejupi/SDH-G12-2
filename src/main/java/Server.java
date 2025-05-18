import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server:");
            System.out.println("Waiting for connections...");

            Socket socket = serverSocket.accept();
            System.out.println("Connection established.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Awaiting credentials...");
            String username = in.readLine();
            String password = in.readLine();

            System.out.println("Credentials received. Verifying...");

            User user = UserDatabase.getUser(username);
            if (user != null) {
                String hashedInput = HashUtil.hashPassword(password);
                if (user.getHashedPassword().equals(hashedInput)) {
                    String token = TokenManager.generateToken(username);
                    System.out.println("Authentication successful. JWT issued.");
                    out.println("SUCCESS:" + token);

                    while (true) {
                        try {
                            String receivedToken = in.readLine();
                            String command = in.readLine();

                            if (!TokenManager.verifyToken(receivedToken)) {
                                out.println("INVALID_TOKEN");
                                break;
                            }

                            if (command.equals("logout")) {
                                out.println("LOGOUT");
                                break;
                            } else if (command.equals("request_data")) {
                                out.println("DATA:{ \"data\": \"This is protected data.\" }");
                            } else {
                                out.println("INVALID_COMMAND");
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading from client: " + e.getMessage());
                            out.println("ERROR: Communication issue");
                            break;
                        }
                    }
                } else {
                    out.println("ERROR:Invalid password.");
                }
            } else {
                out.println("ERROR:User not found.");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
