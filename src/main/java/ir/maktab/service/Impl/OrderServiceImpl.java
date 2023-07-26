package ir.maktab.service.Impl;


import ir.maktab.entity.Orders;
import ir.maktab.repository.Impl.OrderRepositoryImpl;
import ir.maktab.repository.OrderRepository;
import ir.maktab.service.OrderService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

public class OrderServiceImpl implements OrderService,OrderRepository {

    private Session session;
    private Transaction transaction;
private OrderRepository orderRepository;

    public OrderServiceImpl(Session session) {
        this.session = session;
        transaction=session.getTransaction();
        orderRepository=new OrderRepositoryImpl(session);
    }

    @Override
    public Orders save(Orders orders) {
        try {
            transaction.begin();
            orderRepository.save(orders);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            orderRepository.getSession().close();
        }
        return orders;
    }

    @Override
    public Orders update(Orders orders) {
        try {
            transaction.begin();
            orderRepository.update(orders);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            orderRepository.getSession().close();
        }
        return orders;
    }

    @Override
    public Orders remove(Orders orders) {
        try {
            transaction.begin();
            orderRepository.remove(orders);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            orderRepository.getSession().close();
        }
        return orders;
    }

    @Override
    public Collection<Orders> load() {
        return orderRepository.load();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id) ;
    }

    @Override
    public Session getSession() {
        return session;
    }
}
