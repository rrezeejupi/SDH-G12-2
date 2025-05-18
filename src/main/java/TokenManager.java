import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class TokenManager {
    private static final String SECRET_KEY = "mysecret123"; // Secret key for signing
    private static final long EXPIRATION_MS = 300_000; // 5 minutes (in milliseconds)

    // Generate a JWT token for the authenticated user
    public static String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withSubject(username) // Use standard 'sub' claim instead of custom
                    .withIssuedAt(new Date()) // Issued-at timestamp
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS)) // Expiration
                    .sign(algorithm); // Sign with secret key
        } catch (Exception e) {
            System.err.println("Error generating token: " + e.getMessage());
            return null;
        }
    }
    public static boolean verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.err.println("Token verification failed: " + e.getMessage());
            return false;
        }
    }

    // Extract username from JWT token
    public static String getUsernameFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getSubject();
        } catch (Exception e) {
            System.err.println("Error decoding token: " + e.getMessage());
            return null;
        }
    }
}