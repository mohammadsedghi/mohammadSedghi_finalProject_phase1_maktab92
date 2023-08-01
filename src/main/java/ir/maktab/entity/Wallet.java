package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
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
public class Wallet extends BaseEntity<Long> {
    @NotNull(message = "this field must be have value")
    @Pattern(message = "basePrice must be have positive value",regexp = "^[+]?\\d+([.]\\d+)?$")
    Double credit;

}
