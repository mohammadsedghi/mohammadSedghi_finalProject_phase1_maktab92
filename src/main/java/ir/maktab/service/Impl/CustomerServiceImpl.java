package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Address;
import ir.maktab.repository.AddressRepository;
import ir.maktab.service.AddressService;

public class CustomerServiceImpl extends BaseServiceImpl<Address, AddressRepository,Long >
        implements AddressService {
    public CustomerServiceImpl(AddressRepository repository) {
        super(repository);
    }
}
