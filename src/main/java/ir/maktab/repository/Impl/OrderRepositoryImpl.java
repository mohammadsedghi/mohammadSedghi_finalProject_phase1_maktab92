package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Orders;
import ir.maktab.repository.OrderRepository;
import org.hibernate.Session;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Orders,Long>
        implements OrderRepository {
    private final Session session;

    public OrderRepositoryImpl(Session session) {
        super(session);
        this.session = session;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Class<Orders> getEnitytyClass() {
        return Orders.class;
    }
}
