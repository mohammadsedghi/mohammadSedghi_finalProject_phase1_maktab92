package ir.maktab.service;


import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.OrderStatus;
import jakarta.persistence.criteria.Order;

import java.util.Collection;
import java.util.Set;

public interface OrderService {
    Orders submitOrder(Orders orders);
    Collection<Orders> showOrdersToSpecialist(SubDuty subDuty );
    Orders updateOrderToNextLevel(Orders orders, OrderStatus orderStatus);
}
