package ir.maktab.entity;


import jakarta.persistence.*;
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
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer extends Person {
    @OneToOne
    Wallet wallet;
    @Builder
    public Customer(@NotNull(message = "this field must be have value")
                         @Length(message = "firstName must be 100 character", max = 100)
                         @Pattern(message = "province must be just letters", regexp = "^[a-zA-Z]+$") String firstName,
                    @NotNull(message = "this field must be have value")
                    @Length(message = "lastName must be 100 character", max = 100)
                    @Pattern(message = "province must be just letters", regexp = "^[a-zA-Z]+$") String Lastname,
                    @NotNull(message = "this field must be have value")
                         @Length(message = "national id must be 10 digit", min = 10, max = 10)
                         @Pattern(message = "postalCode must be just digit", regexp = "^\\d+$") String nationalId,
                    @NotNull(message = "email must be have value")
                         @Pattern(message = "email is not valid", regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$") String Email,
                    @NotNull(message = "this field must be have value")
                         @Pattern(message = "password is not valid", regexp = "^[a-zA-Z0-9]+$")
                         @Length(message = "password must be 10 character", min = 10, max = 10) String password,
                    @NotNull(message = "this field must be have value") LocalDate registerDate,
                    @NotNull(message = "this field must be have value") LocalTime registerTime, Wallet wallet) {
        super(firstName, Lastname, nationalId, Email, password, registerDate, registerTime);
        this.wallet = wallet;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", registerDate=" + registerDate +
                ", registerTime=" + registerTime +
                "} " + super.toString();
    }
}
