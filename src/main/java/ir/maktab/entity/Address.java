package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address extends BaseEntity<Long> {
    String province;
    String city;
    String street;
    String postalCode;
    int HouseNumber;
}
