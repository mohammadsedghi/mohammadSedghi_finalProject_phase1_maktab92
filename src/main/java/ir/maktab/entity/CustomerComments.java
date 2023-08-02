package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerComments extends BaseEntity<Long> {

    @OneToOne
    Orders orders;
    @Pattern(message = "description of CustomerComments must be just letters",regexp = "^[a-zA-Z]+$")
    @NotNull(message = "description of  CustomerComments must be have value")
    String description;
    @NotNull(message = "score of CustomerComments must be have value")
    Integer score;
}
