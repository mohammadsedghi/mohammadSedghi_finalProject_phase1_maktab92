package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.CustomerComments;
import ir.maktab.repository.CustomerCommentsRepository;
import org.hibernate.Session;

public class CustomerCommentsRepositoryImpl extends BaseRepositoryImpl<CustomerComments,Long>
        implements CustomerCommentsRepository {
    private final Session session;

    public CustomerCommentsRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<CustomerComments> getEnitytyClass() {
        return CustomerComments.class;
    }
}
