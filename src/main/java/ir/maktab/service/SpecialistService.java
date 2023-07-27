package ir.maktab.service;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;

import java.util.Optional;


public interface SpecialistService  {
    Specialist addSpecialist(Specialist specialist);
    Optional<Specialist> loginByEmailAndPassword(String email, String password);
    void confirmSpecialistByAdmin();
    Boolean addSpecialistToSubDuty(Specialist specialist, SubDuty subDuty);
}
