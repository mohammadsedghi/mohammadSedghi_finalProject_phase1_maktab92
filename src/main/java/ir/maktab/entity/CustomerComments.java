package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CustomerComments extends BaseEntity<Long> {

    @OneToOne
    Orders orders;
  String description;
    @NotNull(message = "this field must be have value")
    Integer score;
}
