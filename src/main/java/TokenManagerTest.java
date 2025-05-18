import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenManagerTest {
    public static void main(String[] args) {
        String username = "testUser";

        // Generate token
        String token = TokenManager.generateToken(username);
        System.out.println("Generated Token: " + token);

        // Verify the token is not null and print its parts
        if (token != null) {
            System.out.println("\nToken Parts:");
            String[] parts = token.split("\\.");
            System.out.println("Header: " + parts[0]);
            System.out.println("Payload: " + parts[1]);
            System.out.println("Signature: " + parts[2]);

            // OPTIONAL: Decode token and print the subject (sub)
            DecodedJWT decoded = JWT.decode(token);
            System.out.println("\nDecoded Subject (sub): " + decoded.getSubject());
        } else {
            System.err.println("Token generation failed!");
        }
    }
}
