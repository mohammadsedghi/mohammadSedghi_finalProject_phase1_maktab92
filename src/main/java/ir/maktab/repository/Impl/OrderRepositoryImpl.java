package ir.maktab.repository.Impl;

import ir.maktab.base.repository.BaseRepositoryImpl;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.OrderStatus;
import ir.maktab.repository.OrderRepository;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import java.util.Collection;
import java.util.Optional;


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

    @Override
    public Collection<Orders> showOrdersToSpecialist(SubDuty subDuty ) {
       // String hql="select o from Orders o where o.subDuty=:subDuty and o.orderStatus=:orderStatusٌWaitingForSuggest or  o.orderStatus=:orderStatusWaitingForSelect";
        String hql = "select o from Orders o where o.subDuty = :subDuty and (o.orderStatus = :orderStatusWaitingForSuggest or o.orderStatus = :orderStatusWaitingForSelect)";
        OrderStatus orderStatusWaitingForSuggest=OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION;
        OrderStatus orderStatusWaitingForSelect=OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION;

       return session.createQuery(hql,Orders.class)
                .setParameter("subDuty",subDuty)
               .setParameter("orderStatusWaitingForSuggest",orderStatusWaitingForSuggest)
               .setParameter("orderStatusWaitingForSelect",orderStatusWaitingForSelect)
                .getResultList();

    }

    @Override
    public Optional<Orders> findOrdersWithThisCustomerAndSubDuty(Customer customer, SubDuty subDuty) {
        String hql="select o from Orders o where o.customer=:customer and o.subDuty=:subDuty";
        try {
            return Optional.ofNullable(session.createQuery(hql, Orders.class)
                    .setParameter("customer", customer)
                    .setParameter("subDuty", subDuty)
                    .getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }
    }
}
