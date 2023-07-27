package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.Customer;
import jakarta.validation.constraints.Email;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByEmailAndPassword(String email,String password);
}
