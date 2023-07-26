package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Person;
import ir.maktab.repository.CustomerRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer,Long>
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

}catch(NoResultException e){
   return Optional.empty();
}
    }
}
