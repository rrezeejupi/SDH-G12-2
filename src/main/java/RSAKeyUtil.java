// RSAKeyUtil.java
import java.security.*;

public class RSAKeyUtil {
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }
}
