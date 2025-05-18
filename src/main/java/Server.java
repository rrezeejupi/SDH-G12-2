import java.io.*;
import java.net.*;
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server: Waiting for connections...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Server: Connection established.");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.println("Server: Awaiting credentials...");
        String username = in.readLine();
        String password = in.readLine();

        System.out.println("Server: Credentials received. Verifying...");
        User user = UserDatabase.getUser(username);

        if (user != null && user.getHashedPassword().equals(HashUtil.hashPassword(password))) {
            System.out.println("Server: Authentication successful. JWT issued.");
            String token = TokenManager.generateToken(username);
            out.println("AUTH_SUCCESS:" + token);

            // Handle client commands (request_data / logout)
            while (true) {
                String command = in.readLine();
                if (command.equals("request_data")) {
                    out.println("PROTECTED_DATA: { \"data\": \"This is protected data.\" }");
                } else if (command.equals("logout")) {
                    System.out.println("Server: User logged out.");
                    break;
                }
            }
        } else {
            out.println("AUTH_FAILED:Invalid credentials");
        }

        clientSocket.close();
        serverSocket.close();
    }
}
