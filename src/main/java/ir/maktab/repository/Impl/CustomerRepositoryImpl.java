package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Customer;
import ir.maktab.repository.CustomerRepository;
import org.hibernate.Session;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer,Long>
        implements CustomerRepository {
    private final Session session;

    public CustomerRepositoryImpl(Session session, Session session1) {
        super(session);
        this.session = session1;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Customer> getEnitytyClass() {
        return Customer.class;
    }
}
