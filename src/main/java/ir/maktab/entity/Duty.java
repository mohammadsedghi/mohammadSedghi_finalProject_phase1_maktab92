package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Duty extends BaseEntity<Long> {

    @Length(message ="name of services must be 100 character",max = 100)
    @NotNull(message = "this field must be have value")
    @Pattern(message = "province must be just letters",regexp = "^[a-zA-Z]+$")
    String name;
    @OneToMany(mappedBy = "duty")
    Set<SubDuty> subDuties;

    public Duty(String name) {
        this.name = name;
    }
}
