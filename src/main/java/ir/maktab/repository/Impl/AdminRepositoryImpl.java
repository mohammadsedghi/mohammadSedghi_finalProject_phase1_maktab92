package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Admin;
import ir.maktab.entity.Customer;
import ir.maktab.repository.AdminRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Optional;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin,Long> implements AdminRepository {
    private final Session session;

    public AdminRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Admin> getEnitytyClass() {
        return Admin.class;
    }

    @Override
    public Optional<Admin> findByEmailAndPassword(String email, String password) {
        String hql = "select a from Admin a where a.email=:email and a.password=:password";
        try {
            return Optional.ofNullable(session.createQuery(hql, Admin.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
