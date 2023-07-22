package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import ir.maktab.entity.enumeration.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order extends BaseEntity<Long> {
    @ManyToOne
    Customer customer;
    String description;
    LocalDate DateOfWork;
    LocalDateTime when;
    Address address;
    OrderStatus orderStatus;

}
