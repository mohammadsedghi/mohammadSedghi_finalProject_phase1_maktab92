package ir.maktab.custom_exception;

import java.security.NoSuchAlgorithmException;

public class CustomNoSuchAlgorithmException extends Exception {
    public CustomNoSuchAlgorithmException(String messages) {
        super(messages);
    }
}
