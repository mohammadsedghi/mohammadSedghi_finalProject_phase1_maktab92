package ir.maktab.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRegex {
    String validStr="^[a-zA-Z]+$";
    String validDigit="^\\d+$";
    String ValidDigitStr="^[a-zA-Z0-9]+$";
    String validPositiveDigit="^[+]?\\d+([.]\\d+)?$";
    String validEmptyStr="(^$|^.*@.*\\..*$)";
    public String getErrorMessageForValidStr(String fieldName){
        return fieldName+"must be just letters";
    }
    public String getErrorMessageForValidDigit(String fieldName){
        return fieldName+"must be just digit";
    }
    public String getErrorMessageForValidDigitStr(String fieldName){
        return fieldName+"must be just digit and Str";
    }
    public String getErrorMessageForValidPositiveDigit(Integer fieldName){
        return fieldName+"must be just positive digit";
    }
    public String getErrorMessageForValidEmptyStr(Integer fieldName){
        return fieldName+"must be  have a value but input is  empty";
    }
    public String getErrorMessageForValidNotNullStr(Integer fieldName){
        return fieldName+"must be not null but input is  null";
    }
    public Boolean checkInputIsValid(String input,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        } else {
           return false;
        }
    }
    public Boolean inputIsNull(String input){
        return input == null;
    }

}
