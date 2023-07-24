package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Order;
import ir.maktab.repository.OrderRepository;
import ir.maktab.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl<Order, OrderRepository,Long >
        implements OrderService {
    public OrderServiceImpl(OrderRepository repository) {
        super(repository);
    }
}
