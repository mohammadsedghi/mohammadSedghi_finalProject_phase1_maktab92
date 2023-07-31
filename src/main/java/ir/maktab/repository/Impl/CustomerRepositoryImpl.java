package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Customer;
import ir.maktab.repository.CustomerRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer, Long>
        implements CustomerRepository {
    private final Session session;

    public CustomerRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Customer> getEnitytyClass() {
        return Customer.class;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {

        try {
            String hql = "select c from Customer c where c.email =:email";
            return Optional.of(session.createQuery(hql, Customer.class)
                    .setParameter("email", email)
                    .getSingleResult());

        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        String hql = "select c from Customer c where c.email=:email and c.password=:password";
        try {
            return Optional.ofNullable(session.createQuery(hql, Customer.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
