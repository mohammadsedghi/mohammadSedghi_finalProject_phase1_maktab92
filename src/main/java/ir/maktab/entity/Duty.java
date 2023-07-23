package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Duty extends BaseEntity<Long> {
    @NotNull
    @Length(message ="name of services must be 100 character",max = 100)
    @Pattern(message = "Services must be just letters",regexp = "^[a-zA-Z]+$")
    String name;

}
