package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubServices extends BaseEntity<Long> {
    @ManyToOne
    Services services;
    String name;
    Double price;
    String description;
}
