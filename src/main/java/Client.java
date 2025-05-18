import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Client: Enter username: ");
        String username = scanner.nextLine();
        out.println(username);

        System.out.print("Client: Enter password: ");
        String password = scanner.nextLine();
        out.println(password);

        String response = in.readLine();
        if (response.startsWith("AUTH_SUCCESS")) {
            String token = response.split(":")[1];
            System.out.println("Client: Logged in. JWT token is: " + token);

            while (true) {
                System.out.print("Client: Enter command ('request_data' or 'logout'): ");
                String command = scanner.nextLine();
                out.println(command);

                if (command.equals("logout")) {
                    System.out.println("Client: Logging out…");
                    break;
                } else if (command.equals("request_data")) {
                    String data = in.readLine();
                    System.out.println("Client: Protected data received: " + data);
                }
            }
        } else {
            System.out.println("Client: Login failed.");
        }

        socket.close();
        scanner.close();
    }
}