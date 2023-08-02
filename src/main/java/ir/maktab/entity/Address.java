package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address extends BaseEntity<Long> {
    @NotNull(message = "province must be have value")
    @Pattern(message = "province must be just letters",regexp = "^[a-zA-Z]+$")
    String province;
    @NotNull(message = "city must be have value")
    @Pattern(message = "city must be just letters",regexp = "^[a-zA-Z]+$")
    String city;
    @NotNull(message = "street must be have value")
    @Pattern(message = "street must be just letters",regexp = "^[a-zA-Z]+$")
    String street;
    @NotNull(message = "postalCode must be have value")
    @Pattern(message = "postalCode must be just digit",regexp ="^\\d+$")
    String postalCode;
    @NotNull(message = "postalCode must be have value")
    Integer houseNumber;
}
