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
public class CustomerOpinions extends BaseEntity<Long> {

    @ManyToOne
    Specialist specialist;
    @Positive
    @Pattern(message = "score must be just digit",regexp = "^\\d+$")
    Integer score;
}
