package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.Customer;
import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends BaseRepository<Orders,Long> {
    Collection<Orders> showOrdersToSpecialist(SubDuty subDuty );
    Optional<Orders> findOrdersWithThisCustomerAndSubDuty(Customer customer,SubDuty subDuty);
    Collection<Orders> findOrdersInStatusWaitingForSpecialistSuggestion(Customer customer);
    Collection<Orders> findOrdersInStatusWaitingForSpecialistSelection(Customer customer);
    Collection<Orders> findOrdersInStatusWaitingForSpecialistToWorkplace(Customer customer);
    Collection<Orders> findOrdersInStatusStarted(Customer customer);
    Collection<Orders> findOrdersInStatusPaid(Customer customer);
    Collection<Orders> findOrdersInStatusDone(Customer customer);
}
