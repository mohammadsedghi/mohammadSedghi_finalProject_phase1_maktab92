package ir.maktab.entity;


import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends Person {
    @OneToOne
    Wallet wallet;

}
