package ir.maktab.util.custom_exception;

import jakarta.persistence.NoResultException;

public class CustomNoResultException extends NoResultException {
    public CustomNoResultException(String message) {
        super(message);
    }
}
