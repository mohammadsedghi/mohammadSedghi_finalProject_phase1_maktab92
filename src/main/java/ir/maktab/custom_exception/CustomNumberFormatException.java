package ir.maktab.custom_exception;

public class CustomNumberFormatException extends NumberFormatException{
    public CustomNumberFormatException(String messages) {
        super(messages);
    }
}
