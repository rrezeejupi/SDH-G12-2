import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO: Zevenesoni kete kod me login funksional (krejt nga terminali)
        // Scanner per me marr username edhe password prej user
        // Verifiko passwordin duke e hash inputin edhe me krahasu me stored hash
        // Kur ka login te suksesshem, call JWT token generator
        Scanner scanner = new Scanner(System.in);

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

            } else {
                System.out.println("Fjalëkalimi është i pasaktë.");
            }
        } else {
            System.out.println("User not found.");
        }
        scanner.close();
    }
}