package ir.maktab.service.Impl;

import ir.maktab.entity.Admin;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.repository.AdminRepository;
import ir.maktab.repository.Impl.AdminRepositoryImpl;
import ir.maktab.service.AdminService;
import ir.maktab.util.Menu;
import ir.maktab.util.custom_exception.CustomNoResultException;
import ir.maktab.util.hash_password.EncryptPassword;
import ir.maktab.util.validation.CheckValidation;
import org.hibernate.Session;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {
    CheckValidation checkValidation=new CheckValidation();
    private final Session session;
    AdminRepository adminRepository;

    public AdminServiceImpl(Session session) {
        this.session = session;
        adminRepository=new AdminRepositoryImpl(session);
    }

    @Override
    public Optional<Admin> loginByEmailAndPassword(String email, String password) {
        Menu menu=new Menu();
        try {
            if (checkValidation.isValidEmail(email) && checkValidation.isValidPassword(password)) {
                adminRepository.findByEmailAndPassword(email, encryptAdminPassword(password)).ifPresentOrElse(
                        admin -> {
                            CheckValidation.memberTypeAdmin = admin;
                           menu.runAdminMenu();
                        }
                        , () -> {
                            throw new CustomNoResultException("Admin not found");
                        }
                );
            }else {throw new CustomNoResultException("you inter invalid input for login");}
        }catch (CustomNoResultException c) {
            CheckValidation.memberTypeAdmin =new Admin();
            System.out.println(c.getMessage());
            menu.firstMenu();
        }
        return Optional.empty();
    }

    @Override
    public String encryptAdminPassword(String password) {
        EncryptPassword encryptPassword=new EncryptPassword();
        return encryptPassword.hashPassword(password);
    }
}
