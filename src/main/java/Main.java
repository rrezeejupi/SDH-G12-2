import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // TODO: Zevenesoni kete kod me login funksional (krejt nga terminali)
        // Scanner per me marr username edhe password prej user
        // Verifiko passwordin duke e hash inputin edhe me krahasu me stored hash
        // Kur ka login te suksesshem, call JWT token generator


        System.out.println("=== SISTEMI I LOGIN-IT ===");
        System.out.print("Shkruaj emrin e përdoruesit: ");
        String username = scanner.nextLine();

        System.out.print("Shkruaj fjalëkalimin: ");
        String password = scanner.nextLine();


        User user = UserDatabase.getUser(username);

        if (user != null) {
            String hashedInput = HashUtil.hashPassword(password);
            if (user.getHashedPassword().equals(hashedInput)) {
                System.out.println("Login i suksesshëm!");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Stored Hashed Password: " + user.getHashedPassword());

            //call jwt
                String token = TokenManager.generateToken(username);
                System.out.println("Token-i juaj JWT: " + token);

                // Thirr funksionin për veçorinë e mbrojtur
                handleProtectedFeature();
            } else {
                System.out.println("Fjalëkalimi është i pasaktë.");
            }
        } else {
            System.out.println("User not found.");
        }
        scanner.close();
    }
    private static void handleProtectedFeature() {
        System.out.println("\n=== Protected Feature ===");
        System.out.print("Enter your JWT token: ");
        String token = scanner.nextLine();

        if (TokenManager.verifyToken(token)) {
            String username = TokenManager.getUsernameFromToken(token);
            System.out.println("Access granted to user: " + username);
            System.out.println("Secret data = 42");
        } else {
            System.out.println("Access denied! Invalid or expired token.");
        }
    }
}