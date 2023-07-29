package ir.maktab.util.hash_password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptPassword {
    public  String hashPassword(String password) {
        try {
            // Get SHA-512 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Convert the password string to bytes and hash it
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convert the hashed bytes to a Base64-encoded string for storage
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public  boolean checkPassword(String password, String storedHash) {
        // Hash the entered password and compare it with the stored hash
        String enteredHash = hashPassword(password);
        return storedHash.equals(enteredHash);
    }
}
