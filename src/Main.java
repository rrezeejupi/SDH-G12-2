public class Main {
    public static void main(String[] args) {
        // TODO: Zevenesoni kete kod me login funksional (krejt nga terminali)
        // Scanner per me marr username edhe password prej user
        // Verifiko passwordin duke e hash inputin edhe me krahasu me stored hash
        // Kur ka login te suksesshem, call JWT token generator
        //


        User user = UserDatabase.getUser("alice");
        if (user != null) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Stored Hashed Password: " + user.getHashedPassword());
        } else {
            System.out.println("User not found.");
        }
    }
}