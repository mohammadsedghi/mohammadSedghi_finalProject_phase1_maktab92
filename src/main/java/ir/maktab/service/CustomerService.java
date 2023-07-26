package ir.maktab.service;


import ir.maktab.entity.Customer;

public interface CustomerService  {
    Customer addCustomer(Customer customer);
    Customer removeCustomer(Customer customer);
    Customer updateCustomer(Customer customer);

}
