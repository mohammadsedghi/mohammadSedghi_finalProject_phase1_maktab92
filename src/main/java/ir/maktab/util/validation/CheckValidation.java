package ir.maktab.util.validation;

import ir.maktab.entity.Customer;
import ir.maktab.entity.Specialist;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class CheckValidation {
    public static Customer memberTypeCustomer;
    public static Specialist memberTypespecialist;
    CustomRegex customRegex = new CustomRegex();

    public <T> boolean isValid(T object) {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();

        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (violations.size() > 0) {
            for (ConstraintViolation<T> violation : violations) {
                try {
                    throw new IllegalArgumentException();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    log.error(violation.getMessage());
                }
            }
            factory.close();
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(customRegex.getValidEmail());
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            customRegex.getErrorMessageForValidDigitStr("email");
            return false;
        }
    }

    public boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        Pattern pattern = Pattern.compile(customRegex.getValidDigitStr());
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        } else {
            customRegex.getErrorMessageForValidDigitStr("password");
            return false;
        }
    }

    public boolean isJpgImage(byte[] bytes) {
        // Check the first few bytes of the decoded content to determine if it's a JPEG image
        return (bytes.length >= 2 && bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8);
    }


    public boolean isImageHaveValidSize(byte[] bytes) {
        return bytes.length <= 300000;
    }

}
