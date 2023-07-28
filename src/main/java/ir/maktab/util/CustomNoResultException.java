package ir.maktab.util;

import jakarta.persistence.NoResultException;

public class CustomNoResultException extends NoResultException {
    public CustomNoResultException(String message) {
        super(message);
    }
}
