package ir.maktab.util;

import ir.maktab.util.custom_exception.CustomException;

import java.time.LocalDate;
import java.util.Scanner;

public class CalenderAndValidation {
    public void setDate(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the day:");
        int day = scanner.nextInt();

        System.out.println("Enter the month:");
        int month = scanner.nextInt();

        System.out.println("Enter the year:");
        int year = scanner.nextInt();
        inputConvertToDate(year,month,day);
    }
    public LocalDate inputConvertToDate(int year,int month, int day){
        return LocalDate.of(year, month, day);
    }
    public Boolean isDateForFuturePresentOrNo(LocalDate dateOfNow,LocalDate dateOrPresentOrFuture){
   try{
       if(dateOrPresentOrFuture.isBefore(dateOfNow)){
        throw new CustomException("you must be inter for present or future");
    }
   }catch (CustomException e){
       System.out.println(e.getMessage());
       return false;
   }
   return true;

    }
}
