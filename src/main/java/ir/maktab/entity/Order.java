package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import ir.maktab.entity.enumeration.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Please enter your name this field must be have value")
    String description;
    @FutureOrPresent(message = "Invalid date, It should be as future or present date")
    LocalDate DateOfWork;
    @FutureOrPresent(message = "Invalid Time, It should be as future or present time")
    LocalDateTime when;
    @NotNull(message = "Please enter your name this field must be have value")
    Address address;
    @NotNull(message = "Please enter your name this field must be have value")
    OrderStatus orderStatus;

}
