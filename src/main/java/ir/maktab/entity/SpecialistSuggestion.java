package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
    @ManyToMany
    @JoinTable(
            name="SpecialistSuggestion_orders",
            joinColumns=@JoinColumn(name="SpecialistSuggestion_ID", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="Orders_ID", referencedColumnName="id"))
    Set<Order> order;
    @PastOrPresent(message = "date of Suggestion must be present or past a value")
    @NotNull(message = "DateOfSuggestion field must be have value")
    LocalDate DateOfSuggestion;
    @PastOrPresent(message = "time of Suggestion must be present or past a value")
    @NotNull(message = "TimeOfSuggestion must be have value")
    LocalTime TimeOfSuggestion;
    @Positive(message = "Invalid price, Value should be positive")
    @NotNull(message = "Please enter price because must be have a value")
    Double price;
    @NotNull(message = "TimeOfStartWork field must be have a value")
    LocalTime TimeOfStartWork;
    @NotNull(message = "durationOfWorkPerHour field must be have a value")
    Integer durationOfWorkPerHour;
}
