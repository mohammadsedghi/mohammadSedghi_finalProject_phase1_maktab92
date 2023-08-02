package ir.maktab.util.validation;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Setter
@Getter
public class CustomRegex {
    String validStr = "^[a-zA-Z]+$";
    String validDigit = "^\\d+$";
    String validPrice = "^\\d+(\\.\\d{1,2})?$";
    String validDigitStr = "^[a-zA-Z0-9]+$";
    String validPositiveDigit = "^[+]?\\d+([.]\\d+)?$";
    String validEmptyStr = "(^$|^.*@.*\\..*$)";
    String password = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$";
    String validEmail = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$";

    public String getErrorMessageForValidStr(String fieldName) {
        return fieldName + "must be just letters";
    }

    public String getErrorMessageForValidDigit(String fieldName) {
        return fieldName + "must be just digit";
    }

    public String getErrorMessageForValidDigitStr(String fieldName) {
        return fieldName + "must be just digit and Str";
    }

    public String getErrorMessageForValidPositiveDigit(Integer fieldName) {
        return fieldName + "must be just positive digit";
    }

    public String getErrorMessageForValidEmptyStr(String fieldName) {
        return fieldName + "must be  have a value but input is  empty";
    }

    public String getErrorMessageForValidNotNullStr(String fieldName) {
        return fieldName + "must be not null but input is  null";
    }

    public String getErrorMessageForValidEmail(String email) {
        return email + "must be not null but input is  invalid or null";
    }

    public Boolean checkOneInputIsValid(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }




    public Boolean checkTwoInputIsValid(String inputOne, String regexForInputOne, String inputTwo, String regexForInputTwo) {
        Pattern patternOne = Pattern.compile(regexForInputOne);
        Matcher matcherOne = patternOne.matcher(inputOne);
        Pattern patternTwo = Pattern.compile(regexForInputTwo);
        Matcher matcherTwo = patternTwo.matcher(inputTwo);
        if (matcherOne.matches() && matcherTwo.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean inputIsNull(String input) {
        return input == null;
    }

}
