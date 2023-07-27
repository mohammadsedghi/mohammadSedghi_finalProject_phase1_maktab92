package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Specialist;

import java.util.Collection;
import java.util.Optional;

public interface SpecialistRepository extends BaseRepository<Specialist,Long> {
    Optional<Specialist> findByEmail(String email);
    Optional<Specialist> findByEmailAndPassword(String email,String password);
Collection<Specialist> showUnConfirmSpecialist();
}
