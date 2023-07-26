package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Address;
import ir.maktab.repository.AddressRepository;
import org.hibernate.Session;

public class AddressRepositoryImpl extends BaseRepositoryImpl<Address,Long> implements AddressRepository {
    private final Session session;

    public AddressRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }
    @Override
    public Class<Address> getEnitytyClass() {
        return Address.class;
    }
}
