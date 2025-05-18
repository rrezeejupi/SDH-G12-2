import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class TokenManager {
    private static final long EXPIRATION_MS = 300_000; // 5 minutes
    private static final KeyPair KEY_PAIR = RSAKeyUtil.generateKeyPair();

    public static String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) KEY_PAIR.getPublic(), (RSAPrivateKey) KEY_PAIR.getPrivate());
            return JWT.create()
                    .withSubject(username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                    .sign(algorithm);
        } catch (Exception e) {
            System.err.println("Error generating token: " + e.getMessage());
            return null;
        }
    }

    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) KEY_PAIR.getPublic(), null);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.err.println("Token verification failed: " + e.getMessage());
            return false;
        }
    }

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
