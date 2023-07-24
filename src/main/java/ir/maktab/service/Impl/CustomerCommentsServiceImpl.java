package ir.maktab.service.Impl;

import ir.maktab.base.service.BaseServiceImpl;
import ir.maktab.entity.Address;
import ir.maktab.entity.CustomerComments;
import ir.maktab.repository.AddressRepository;
import ir.maktab.repository.CustomerCommentsRepository;
import ir.maktab.service.AddressService;
import ir.maktab.service.CustomerCommentsService;

public class CustomerCommentsServiceImpl extends BaseServiceImpl<CustomerComments, CustomerCommentsRepository,Long >
        implements CustomerCommentsService {
    public CustomerCommentsServiceImpl(CustomerCommentsRepository repository) {
        super(repository);
    }
}
