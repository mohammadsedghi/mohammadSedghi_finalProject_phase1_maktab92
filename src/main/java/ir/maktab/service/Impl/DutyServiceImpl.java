package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Address;
import ir.maktab.entity.Duty;
import ir.maktab.repository.AddressRepository;
import ir.maktab.repository.DutyRepository;
import ir.maktab.service.AddressService;
import ir.maktab.service.DutyService;

public class DutyServiceImpl extends BaseServiceImpl<Duty, DutyRepository,Long >
        implements DutyService {
    public DutyServiceImpl(DutyRepository repository) {
        super(repository);
    }
}
