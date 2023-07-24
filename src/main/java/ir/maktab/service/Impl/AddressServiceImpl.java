package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Address;
import ir.maktab.repository.AddressRepository;
import ir.maktab.service.AddressService;

public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRepository,Long >
        implements AddressService {
    public AddressServiceImpl(AddressRepository repository) {
        super(repository);
    }
}
