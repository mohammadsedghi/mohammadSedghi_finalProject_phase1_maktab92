package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;


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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecialistSuggestion extends BaseEntity<Long> {
    @ManyToOne
    Specialist specialist;
    @ManyToOne
    Orders order;
    @NotNull(message = "DateOfSuggestion must be have value")
    LocalDate DateOfSuggestion;
    @NotNull(message = "TimeOfSuggestion must be have value")
    LocalTime TimeOfSuggestion;
    @NotNull(message = "proposedPrice must be have value")
    Double proposedPrice;
    @NotNull(message = "TimeOfStartWork must be have value")
    LocalTime TimeOfStartWork;
    @NotNull(message = "durationOfWorkPerHour must be have value")
    Integer durationOfWorkPerHour;
}
