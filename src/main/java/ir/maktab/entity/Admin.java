package ir.maktab.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
}
