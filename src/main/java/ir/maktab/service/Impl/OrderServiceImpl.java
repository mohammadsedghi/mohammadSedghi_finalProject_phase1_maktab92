package ir.maktab.service.Impl;


import ir.maktab.entity.Customer;
import ir.maktab.entity.Orders;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.OrderStatus;
import ir.maktab.repository.Impl.OrderRepositoryImpl;
import ir.maktab.repository.OrderRepository;
import ir.maktab.service.OrderService;
import ir.maktab.util.custom_exception.CustomDuplicateInfoException;
import ir.maktab.util.validation.CustomRegex;
import ir.maktab.veiw.Menu;
import ir.maktab.util.custom_exception.CustomException;
import ir.maktab.util.validation.CheckValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final Session session;
    private Transaction transaction;
    private OrderRepository orderRepository;

    public OrderServiceImpl(Session session) {
        this.session = session;
        transaction = session.getTransaction();
        orderRepository = new OrderRepositoryImpl(session);
    }

    public Orders updateOrderToNextLevel(Orders orders, OrderStatus orderStatus) {
        orders.setOrderStatus(orderStatus);
        orderRepository.update(orders);
        return orders;
    }

    @Override
    public void findOrdersWithThisCustomerAndSubDuty(Customer customer, SubDuty subDuty) {
        Menu menu = new Menu();
        orderRepository.findOrdersWithThisCustomerAndSubDuty(customer, subDuty).ifPresent(
                orders -> {
                    try {
                        throw new CustomDuplicateInfoException("this customer submit order for this subDuty");
                    } catch (CustomDuplicateInfoException cdi) {
                        System.out.println(cdi.getMessage());
                        menu.customerMenu();
                    }
                });
    }

    @Override
    public Collection<Orders> findOrdersInStatusWaitingForSpecialistSuggestion(Customer customer) {
        return orderRepository.findOrdersInStatusWaitingForSpecialistSuggestion(customer);
    }

    @Override
    public Collection<Orders> findOrdersInStatusWaitingForSpecialistSelection(Customer customer) {
        return orderRepository.findOrdersInStatusWaitingForSpecialistSelection(customer);
    }

    @Override
    public Collection<Orders> findOrdersInStatusWaitingForSpecialistToWorkplace(Customer customer) {
        return orderRepository.findOrdersInStatusWaitingForSpecialistToWorkplace(customer);
    }

    @Override
    public Collection<Orders> findOrdersInStatusStarted(Customer customer) {
        return orderRepository.findOrdersInStatusStarted(customer);
    }

    @Override
    public Collection<Orders> findOrdersInStatusPaid(Customer customer) {
        return orderRepository.findOrdersInStatusPaid(customer);
    }

    @Override
    public Collection<Orders> findOrdersInStatusDone(Customer customer) {
        return orderRepository.findOrdersInStatusDone(customer);
    }


    public Orders remove(Orders orders) {
        try {
            transaction.begin();
            orderRepository.remove(orders);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return orders;
    }


    public Collection<Orders> load() {
        return orderRepository.load();
    }


    public Optional<Orders> findById(Long id) {
        return orderRepository.findById(id);
    }


    @Override
    public Orders submitOrder(Orders orders, String priceOfOrder, String subDutyBasePrice) {
        CheckValidation checkValidation = new CheckValidation();
        CustomRegex customRegex = new CustomRegex();
        Menu menu = new Menu();
        try {
            if (!customRegex.checkOneInputIsValid(priceOfOrder, customRegex.getValidPrice())) {
                throw new CustomException("input ProposedPrice for orders is invalid");
            } else {
                orders.setProposedPrice(Double.parseDouble(priceOfOrder) + Double.parseDouble(subDutyBasePrice));
            }
            if (!checkValidation.isValid(orders)) {
                throw new CustomException("input for orders is invalid");
            }
            if (!checkValidation.isValid(orders.getAddress())) {
                throw new CustomException("input address for orders is invalid");
            }
            try {
                transaction.begin();
                orderRepository.save(orders);
                transaction.commit();
            } catch (TransactionException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }

        } catch (CustomException e) {
            System.out.println(e.getMessage());
            menu.submitOrders();
        }
        return orders;
    }

    @Override
    public Collection<Orders> showOrdersToSpecialist(SubDuty subDuty) {
        return orderRepository.showOrdersToSpecialist(subDuty);
    }
}
