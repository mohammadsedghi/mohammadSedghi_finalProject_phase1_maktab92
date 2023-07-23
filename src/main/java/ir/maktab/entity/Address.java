package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Embeddable
public class Address extends BaseEntity<Long> {
    @NotNull(message = "Please enter your name this field must be have value")
    @Pattern(message = " province must be just letters",regexp = "^[a-zA-Z]+$")
    String province;
    @NotNull(message = "Please enter your name this field must be have value")
    @Pattern(message = "city must be just letters",regexp = "^[a-zA-Z]+$")
    String city;
    @NotNull(message = "Please enter your name this field must be have value")
    @Pattern(message = "street must be just letters",regexp = "^[a-zA-Z]+$")
    String street;
    @NotNull(message = "Please enter your name this field must be have value")
    @Pattern(message = "postalCode must be just digit",regexp = "^\\d+$")
    String postalCode;
    @NotNull(message = "Please enter your name this field must be have value")
    @Pattern(message = "HouseNumber must be just digit",regexp = "^\\d+$")
    Integer HouseNumber;
}
