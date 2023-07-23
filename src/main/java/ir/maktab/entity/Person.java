package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity<Long> {
    @Pattern(message = "firstName must be just letters",regexp = "^[a-zA-Z]+$")
    @Length(message ="firstName must be 100 character",max = 100)
    String firstName;
    @Pattern(message = "Lastname must be just letters",regexp = "^[a-zA-Z]+$")
    @Length(message ="lastName must be 100 character",max = 100)
    String Lastname;
    @Pattern(message = "nationalId must be just digit",regexp = "^\\d+$")
    @Length(message = "national id must be 10 digit",min = 10, max = 10)
    String nationalId;
    @Pattern(message = "email is not valid,test@example.com",regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$")
    String Email;
    @Pattern(message = "just letters-digit is allowed",regexp = "^[a-zA-Z0-9]+$")
    @Length(message = "password must be 10 character",min = 10, max = 10)
    String password;
    @Past(message = "Date must be at past")
    LocalDate registerDate;
    @Past(message = "Date must be at past")
    LocalTime registerTime;
}
