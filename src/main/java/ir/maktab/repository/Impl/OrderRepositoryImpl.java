package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Order;
import ir.maktab.repository.OrderRepository;
import org.hibernate.Session;

public class OrderRepositoryImpl extends BaseRepositoryImpl<Order,Long>
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
    public Class<Order> getEnitytyClass() {
        return Order.class;
    }
}
