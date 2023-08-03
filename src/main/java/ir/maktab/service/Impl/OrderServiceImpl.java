package ir.maktab.service.Impl;


import ir.maktab.custom_exception.CustomNoResultException;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Orders;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.OrderStatus;
import ir.maktab.repository.Impl.OrderRepositoryImpl;
import ir.maktab.repository.OrderRepository;
import ir.maktab.service.OrderService;
import ir.maktab.custom_exception.CustomDuplicateInfoException;
import ir.maktab.util.validation.CustomRegex;
import ir.maktab.veiw.Menu;
import ir.maktab.custom_exception.CustomException;
import ir.maktab.util.validation.CheckValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * this class design for work with order instance and all thing that related with order.
 *  * Crud method is implemented
 *  * and other required method that use orderRepository to occur something(read,write)in database
 *  *
 */
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
        Set<Orders> customOrderSet = new HashSet<>(orderRepository.findOrdersInStatusWaitingForSpecialistSuggestion(customer));
        try {
            if (customOrderSet.size() == 0) {
                throw new CustomNoResultException("no order exist with this request");
            }

        } catch (CustomNoResultException cnr) {
            System.out.println(cnr.getMessage());
        }
        return orderRepository.findOrdersInStatusWaitingForSpecialistSuggestion(customer);

    }

    @Override
    public Collection<Orders> findOrdersInStatusWaitingForSpecialistSelection(Customer customer) {
        Set<Orders> customOrderSet = new HashSet<>(orderRepository.findOrdersInStatusWaitingForSpecialistSelection(customer));
        try {
            if (customOrderSet.size() == 0) {
                throw new CustomNoResultException("no order exist with this request");
            }

        } catch (CustomNoResultException cnr) {
            System.out.println(cnr.getMessage());
        }
        return orderRepository.findOrdersInStatusWaitingForSpecialistSelection(customer);

    }

    @Override
    public Collection<Orders> findOrdersInStatusWaitingForSpecialistToWorkplace(Customer customer) {
        Set<Orders> customOrderSet = new HashSet<>(orderRepository.findOrdersInStatusWaitingForSpecialistToWorkplace(customer));
        try {
            if (customOrderSet.size() == 0) {
                throw new CustomNoResultException("no order exist with this request");
            }

        } catch (CustomNoResultException cnr) {
            System.out.println(cnr.getMessage());
        }
        return orderRepository.findOrdersInStatusWaitingForSpecialistToWorkplace(customer);

    }

    @Override
    public Collection<Orders> findOrdersInStatusStarted(Customer customer) {
        Set<Orders> customOrderSet = new HashSet<>(orderRepository.findOrdersInStatusStarted(customer));
        try {
            if (customOrderSet.size() == 0) {
                throw new CustomNoResultException("no order exist with this request");
            }

        } catch (CustomNoResultException cnr) {
            System.out.println(cnr.getMessage());
        }
        return orderRepository.findOrdersInStatusStarted(customer);

    }

    @Override
    public Collection<Orders> findOrdersInStatusPaid(Customer customer) {
        Set<Orders> customOrderSet = new HashSet<>(orderRepository.findOrdersInStatusPaid(customer));
        try {
            if (customOrderSet.size() == 0) {
                throw new CustomNoResultException("no order exist with this request");
            }

        } catch (CustomNoResultException cnr) {
            System.out.println(cnr.getMessage());
        }
        return orderRepository.findOrdersInStatusPaid(customer);

    }

    @Override
    public Collection<Orders> findOrdersInStatusDone(Customer customer) {
        Set<Orders> customOrderSet = new HashSet<>(orderRepository.findOrdersInStatusDone(customer));
        try {
            if (customOrderSet.size() == 0) {
                throw new CustomNoResultException("no order exist with this request");
            }

        } catch (CustomNoResultException cnr) {
            System.out.println(cnr.getMessage());
        }
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
