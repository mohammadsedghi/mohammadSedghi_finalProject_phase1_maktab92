package ir.maktab.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Admin extends Person {
    @Override
    public String toString() {
        return "Admin{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", registerDate=" + registerDate +
                ", registerTime=" + registerTime +
                "} " + super.toString();
    }
@Builder
    public Admin(@NotNull(message = "this field must be have value")
                 @Length(message = "firstName must be 100 character", max = 100)
                 @Pattern(message = "firstName must be just letters", regexp = "^[a-zA-Z]+$") String firstName,
                 @NotNull(message = "this field must be have value")
                 @Length(message = "lastName must be 100 character", max = 100)
                 @Pattern(message = "lastname must be just letters", regexp = "^[a-zA-Z]+$") String lastname,
                 @NotNull(message = "this field must be have value")
                 @Length(message = "national id must be 10 digit", min = 10, max = 10)
                 @Pattern(message = "nationalId must be just digit", regexp = "^\\d+$") String nationalId,
                 @NotNull(message = "email must be have value")
                 @Pattern(message = "email is not valid", regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$") String email,
                 @NotNull(message = "this field must be have value")
                 @Pattern(message = "password is not valid", regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")
                 @Length(message = "password must be 10 character", min = 8, max = 8) String password,
                 @NotNull(message = "this field must be have value") LocalDate registerDate,
                 @NotNull(message = "this field must be have value") LocalTime registerTime) {
        super(firstName, lastname, nationalId, email, password, registerDate, registerTime);
    }
}
