package ir.maktab.util;

import ir.maktab.custom_exception.CustomException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class CalenderAndValidation {
    private LocalDate temporaryDate;
    private LocalTime temporaryTime;
    public LocalDate setAndConvertDate(LocalDate localDateOfCustomerEntered){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the day:");
            int day = scanner.nextInt();

            System.out.println("Enter the month:");
            int month = scanner.nextInt();

            System.out.println("Enter the year:");
            int year = scanner.nextInt();
            temporaryDate=LocalDate.of(year, month, day);
            if(temporaryDate.isBefore(localDateOfCustomerEntered))
                throw new CustomException("you must be inter for present or future");
        }catch (CustomException e){
            System.out.println(e.getMessage());
            return localDateOfCustomerEntered;
        }
        return temporaryDate;
    }
    public LocalTime setAndConvertTime(LocalTime localTimeOfCustomerEntered){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the hour:");
            int hour = scanner.nextInt();

            System.out.println("Enter the minutes:");
            int minutes = scanner.nextInt();

            temporaryTime=LocalTime.of(hour, minutes, 0);
            if(temporaryTime.isBefore(localTimeOfCustomerEntered))
                throw new CustomException("you must be inter for present or future");
        }catch (CustomException e){
            System.out.println(e.getMessage());
            return localTimeOfCustomerEntered;
        }
        return temporaryTime;
    }
}
