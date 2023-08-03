package ir.maktab.entity;

import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Setter
@Getter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Specialist extends Person {
    @ManyToOne
    Duty duty;
    @ManyToMany
    @JoinTable(
            name = "Specialist_SubDuties",
            joinColumns = @JoinColumn(name = "Specialist_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "SubDuties_ID", referencedColumnName = "id"))
    Set<SubDuty> subDuties;
    @OneToOne
    Wallet wallet;
    @Enumerated(EnumType.STRING)
    SpecialistRegisterStatus status;
    @NotNull(message = "score must be have value")
    Integer score;
    @Column(name = "image_data", columnDefinition = "TEXT")
    String imageData;

    @Override
    public String toString() {
        return "Specialist{" +
                "subDuties=" + subDuties +
                ", wallet=" + wallet +
                ", score=" + score +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", registerDate=" + registerDate +
                ", registerTime=" + registerTime +
                "} ";
    }
@Builder
    public Specialist(@NotNull(message = "firstName must be have value")
                      @Length(message = "firstName must be 100 character", max = 100)
                      @Pattern(message = "firstName must be just letters", regexp = "^[a-zA-Z]+$") String firstName,
                      @NotNull(message = "lastName must be have value")
                      @Length(message = "lastName must be 100 character", max = 100)
                      @Pattern(message = "lastname must be just letters", regexp = "^[a-zA-Z]+$") String lastname,
                      @NotNull(message = "nationalId must be have value")
                      @Length(message = "national id must be 10 digit", min = 10, max = 10)
                      @Pattern(message = "nationalId must be just digit", regexp = "^\\d+$") String nationalId,
                      @NotNull(message = "email must be have value")
                      @Pattern(message = "email is not valid", regexp = "^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,5})?$") String email,
                      @NotNull(message = "password must be have value")
                      @Pattern(message = "password is not valid", regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")
                      @Length(message = "password must be 10 character", min = 8, max = 8) String password,
                      @NotNull(message = "registerDate must be have value") LocalDate registerDate,
                      @NotNull(message = "registerTime must be have value") LocalTime registerTime, Duty duty, Set<SubDuty> subDuties, Wallet wallet, SpecialistRegisterStatus status, Integer score, String imageData) {
        super(firstName, lastname, nationalId, email, password, registerDate, registerTime);
        this.duty = duty;
        this.subDuties = subDuties;
        this.wallet = wallet;
        this.status = status;
        this.score = score;
        this.imageData = imageData;
    }
}
