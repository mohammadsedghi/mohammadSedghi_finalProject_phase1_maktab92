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
@Builder
public class SpecialistSuggestion extends BaseEntity<Long> {
    @ManyToOne
    Specialist specialist;
//    @ManyToMany
//    @JoinTable(
//            name="specialistSuggestion_orders",
//            joinColumns=@JoinColumn(name="specialistSuggestion_ID", referencedColumnName="id"),
//            inverseJoinColumns=@JoinColumn(name="orders_ID" , referencedColumnName="id"))
//    Set<Orders> orders;
    @ManyToOne
    Orders order;
    @NotNull(message = "this field must be have value")
    LocalDate DateOfSuggestion;
    @NotNull(message = "this field must be have value")
    LocalTime TimeOfSuggestion;
    @NotNull(message = "this field must be have value")
    Double proposedPrice;
    @NotNull(message = "this field must be have value")
    LocalTime TimeOfStartWork;
    @NotNull(message = "this field must be have value")
    Integer durationOfWorkPerHour;
}
