package ir.maktab.entity;

import ir.maktab.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Specialist extends Person {

    @ManyToMany
    @JoinTable(
            name="Specialist_SubServices",
            joinColumns=@JoinColumn(name="Specialist_ID", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="SubServices_ID", referencedColumnName="id"))
    Set<SubServices> subServices;
    @OneToOne
    Wallet wallet;
    @Lob
    @Column(name = "image_data")
     Byte[] imageData;
}
