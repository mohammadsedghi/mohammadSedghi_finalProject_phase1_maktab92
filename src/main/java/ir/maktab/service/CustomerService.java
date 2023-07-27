package ir.maktab.service;


import ir.maktab.entity.Customer;

import java.util.Optional;

public interface CustomerService  {
    Customer addCustomer(Customer customer);
    Customer removeCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
   Optional<Customer> loginByEmailAndPassword(String email,String password);
}
