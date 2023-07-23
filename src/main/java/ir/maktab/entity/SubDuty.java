package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubDuty extends BaseEntity<Long> {
    @ManyToOne
    Duty duty;
    @NotNull(message = "Please enter your name this field must be have value")
    @Pattern(message = "SubServices name must be just letters",regexp = "^[a-zA-Z]+$")
    String name;
    @Positive(message = "Invalid Rating, Value should be positive")
    Double price;
    @NotNull(message = "Please enter your name this field must be have value")
    String description;
}
