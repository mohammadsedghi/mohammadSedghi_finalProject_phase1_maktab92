package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SpecialistSuggestion extends BaseEntity<Long> {
//    @ManyToMany
//    @JoinTable(
//            name="specialistSuggestion_orders",
//            joinColumns=@JoinColumn(name="specialistSuggestion_ID"),
//            inverseJoinColumns=@JoinColumn(name="orders_ID"))
//    Set<Order> orders;
    //, referencedColumnName="id"
    //, referencedColumnName="id"
    @NotNull(message = "this field must be have value")
    LocalDate DateOfSuggestion;
    @NotNull(message = "this field must be have value")
    LocalTime TimeOfSuggestion;
    @NotNull(message = "this field must be have value")
    Double price;
    @NotNull(message = "this field must be have value")
    LocalTime TimeOfStartWork;
    @NotNull(message = "this field must be have value")
    Integer durationOfWorkPerHour;
}
