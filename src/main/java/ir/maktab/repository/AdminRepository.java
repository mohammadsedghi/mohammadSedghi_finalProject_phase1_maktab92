package ir.maktab.repository;


import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.Admin;

import java.util.Optional;

public interface AdminRepository  extends BaseRepository<Admin,Long> {
    Optional<Admin> findByEmailAndPassword(String email, String password);
}
