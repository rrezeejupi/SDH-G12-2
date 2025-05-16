public class Main {
    public static void main(String[] args) {
        User user = UserDatabase.getUser("alice");
        if (user != null) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Stored Hashed Password: " + user.getHashedPassword());
        } else {
            System.out.println("User not found.");
        }
    }
}