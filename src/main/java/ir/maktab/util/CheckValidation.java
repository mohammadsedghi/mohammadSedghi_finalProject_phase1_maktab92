package ir.maktab.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class CheckValidation {
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

}
