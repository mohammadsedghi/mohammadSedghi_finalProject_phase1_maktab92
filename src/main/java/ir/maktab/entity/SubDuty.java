package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubDuty extends BaseEntity<Long> {
    @ManyToOne
    Duty duty;
    @NotNull(message = "this field must be have value")
    @Pattern(message = "province must be just letters",regexp = "^[a-zA-Z]+$")
    @Length(message ="lastName must be 100 character",max = 100)
    String name;
    @NotNull(message = "this field must be have value")
   // @Pattern(message = "basePrice must be have positive value",regexp = "^[+]?\\d+([.]\\d+)?$")
    Double basePrice;
    @NotNull(message = "this field must be have value")
    @Pattern(message = "province must be just letters",regexp = "^[a-zA-Z]+$")
    @Length(message ="lastName must be 100 character",max = 100)
    String description;
}
