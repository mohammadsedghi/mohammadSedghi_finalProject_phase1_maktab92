package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Specialist;
import ir.maktab.repository.SpecialistRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;

import java.util.Optional;

public class SpecialistRepositoryImpl extends BaseRepositoryImpl<Specialist,Long>
        implements SpecialistRepository {

    private final Session session;

    public SpecialistRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Specialist> getEnitytyClass() {
        return Specialist.class;
    }

    @Override
    public Optional<Specialist> findByEmail(String email) {
        try {
            String hql = "select s from Specialist s where s.email =:email";
            return Optional.of(session.createQuery(hql, Specialist.class)
                    .setParameter("email", email)
                    .getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}