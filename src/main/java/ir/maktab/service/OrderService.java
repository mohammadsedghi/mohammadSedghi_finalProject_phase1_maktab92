package ir.maktab.service;


import ir.maktab.entity.Customer;
import ir.maktab.entity.Orders;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.OrderStatus;

import java.util.Collection;

public interface OrderService {
    Orders submitOrder(Orders orders,String priceOfOrder,String subDutyBasePrice);
    Collection<Orders> showOrdersToSpecialist(SubDuty subDuty );
    Orders updateOrderToNextLevel(Orders orders, OrderStatus orderStatus);
   void findOrdersWithThisCustomerAndSubDuty(Customer customer, SubDuty subDuty) ;
    Collection<Orders> findOrdersInStatusWaitingForSpecialistSuggestion(Customer customer);
    Collection<Orders> findOrdersInStatusWaitingForSpecialistSelection(Customer customer);
    Collection<Orders> findOrdersInStatusWaitingForSpecialistToWorkplace(Customer customer);
    Collection<Orders> findOrdersInStatusStarted(Customer customer);
    Collection<Orders> findOrdersInStatusPaid(Customer customer);
    Collection<Orders> findOrdersInStatusDone(Customer customer);
}
