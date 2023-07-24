package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.SubDuty;
import ir.maktab.repository.SubDutyRepository;
import ir.maktab.service.SubDutyService;

public class SubDutyServiceImpl extends BaseServiceImpl<SubDuty, SubDutyRepository,Long >
        implements SubDutyService {
    public SubDutyServiceImpl(SubDutyRepository repository) {
        super(repository);
    }

}