package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Setter
@Getter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Duty extends BaseEntity<Long> {

    @Length(message ="name of duty must be 100 character",max = 100)
    @NotNull(message = "name must of duty be have value")
    @Pattern(message = "name must be just letters",regexp = "^[a-zA-Z]+$")
    String name;
    @OneToMany(mappedBy = "duty")
    Set<SubDuty> subDuties;

    public Duty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Duty{" +
                "name='" + name + '\'' +
                ", subDuties=" + subDuties +
                "} " ;
    }
}
