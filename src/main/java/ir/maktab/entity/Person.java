package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import ir.maktab.entity.enumeration.Role;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person extends BaseEntity<Long> {
    String firstName;
    String Lastname;
    String nationalId;
    String Email;
    String password;
    Role role;
    LocalDate registerDate;
    LocalTime registerTime;
}
