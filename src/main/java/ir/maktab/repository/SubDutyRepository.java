package ir.maktab.repository;

import ir.maktab.base.repository.BaseRepository;
import ir.maktab.entity.Duty;
import ir.maktab.entity.SubDuty;

import java.util.Collection;
import java.util.Optional;

public interface SubDutyRepository extends BaseRepository<SubDuty,Long> {
    Collection<SubDuty> showSubDutyOfDuty(Duty duty);
    Optional<SubDuty> isExistSubDuty(String name);

}
