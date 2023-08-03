package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import ir.maktab.entity.enumeration.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders extends BaseEntity<Long> {
    @ManyToOne
    Customer customer;
    @ManyToOne
    Specialist specialist;
    @ManyToOne
    SubDuty subDuty;
    @NotNull(message = "proposedPrice must be have value")
//    @Pattern(message = "basePrice must be have positive value",regexp = "^[+]?\\d+([.]\\d+)?$")
    Double proposedPrice;
    @Pattern(message = "description must be just letters",regexp = "^[a-zA-Z]+$")
    @NotNull(message = "description must be have value")
    String description;
    @NotNull(message = "DateOfWork must be have value")
    @FutureOrPresent(message = "date must be for present or future")
    LocalDate DateOfWork;
    @NotNull(message = "timeOfWork must be have value")
    LocalTime timeOfWork;
    @OneToOne
    Address address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Override
    public String toString() {
        return "Orders{" +
                "customer=" + customer.getLastname()+
                ", specialist=" + specialist +
                ", subDuty=" + subDuty +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", DateOfWork=" + DateOfWork +
                ", timeOfWork=" + timeOfWork +
                ", address=" + address +
                ", orderStatus=" + orderStatus +
                "} " + super.toString();
    }
}
