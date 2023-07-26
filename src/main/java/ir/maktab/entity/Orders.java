package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import ir.maktab.entity.enumeration.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders extends BaseEntity<Long> {
    @ManyToOne
    Customer customer;
    @Pattern(message = "province must be just letters",regexp = "^[a-zA-Z]+$")
    @Pattern(message = "province must be just letters",regexp = "^[a-zA-Z]+$")
    @NotNull(message = "this field must be have value")
    String description;
    @NotNull(message = "this field must be have value")
    LocalDate DateOfWork;
    @NotNull(message = "this field must be have value")
    LocalDateTime timeOfWork;
//    @NotNull(message = "this field must be have value")
    @OneToOne
    Address address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

}
