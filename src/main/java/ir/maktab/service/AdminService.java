package ir.maktab.service;

import ir.maktab.entity.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> loginByEmailAndPassword(String email, String password);
    String encryptAdminPassword(String password);
}
