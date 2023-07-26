package ir.maktab.entity;

import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;
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
            name = "Specialist_SubServices",
            joinColumns = @JoinColumn(name = "Specialist_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "SubServices_ID", referencedColumnName = "id"))
    Set<SubDuty> subDuties;
    @OneToOne
    Wallet wallet;
    @Enumerated(EnumType.STRING)
    SpecialistRegisterStatus status;
    @Lob
    @Column(name = "image_data")
    byte[] imageData;

    @Builder
    public Specialist(@NotNull(message = "this field must be have value") @Length(message = "firstName must be 100 character", max = 100) @Pattern(message = "province must be just letters", regexp = "^[a-zA-Z]+$") String firstName, @NotNull(message = "this field must be have value") @Length(message = "lastName must be 100 character", max = 100) @Pattern(message = "province must be just letters", regexp = "^[a-zA-Z]+$") String lastname, @NotNull(message = "this field must be have value") @Length(message = "national id must be 10 digit", min = 10, max = 10) @Pattern(message = "postalCode must be just digit", regexp = "^\\d+$") String nationalId, @NotNull(message = "email must be have value") @Pattern(message = "email is not valid", regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$") String email, @NotNull(message = "this field must be have value") @Pattern(message = "password is not valid", regexp = "^[a-zA-Z0-9]+$") @Length(message = "password must be 10 character", min = 10, max = 10) String password, @NotNull(message = "this field must be have value") LocalDate registerDate, @NotNull(message = "this field must be have value") LocalTime registerTime, Set<SubDuty> subDuties, Wallet wallet, SpecialistRegisterStatus status, byte[] imageData) {
        super(firstName, lastname, nationalId, email, password, registerDate, registerTime);
        this.subDuties = subDuties;
        this.wallet = wallet;
        this.status = status;
        this.imageData = imageData;
    }
}
