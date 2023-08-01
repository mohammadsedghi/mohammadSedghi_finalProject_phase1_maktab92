package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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
  String description;
    @NotNull(message = "this field must be have value")
    Integer score;
}
