package ir.maktab.service;


import ir.maktab.entity.Customer;
import ir.maktab.entity.Orders;
import jakarta.persistence.criteria.Order;

import java.util.Optional;

public interface CustomerService  {
    Customer addCustomer(Customer customer);
    Customer removeCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
   Optional<Customer> loginByEmailAndPassword(String email,String password);
    boolean changePassword(String email,String oldPassword,String newPassword);
    String encryptCustomerPassword(String password);

}
