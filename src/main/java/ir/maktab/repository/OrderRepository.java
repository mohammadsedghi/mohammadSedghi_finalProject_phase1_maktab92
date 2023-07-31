package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;

import java.util.Collection;
import java.util.Set;

public interface OrderRepository extends BaseRepository<Orders,Long> {
    Collection<Orders> showOrdersToSpecialist(SubDuty subDuty );
}
