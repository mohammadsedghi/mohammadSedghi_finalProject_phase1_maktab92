package ir.maktab.service.Impl;


import ir.maktab.entity.Customer;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.repository.Impl.CustomerRepositoryImpl;
import ir.maktab.service.CustomerService;
import ir.maktab.util.CheckValidation;

import ir.maktab.util.CustomException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.util.Collection;
import java.util.Optional;




public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private Session session;
CheckValidation checkValidation=new CheckValidation();

    public CustomerServiceImpl(Session session) {
        this.session = session;
        customerRepository = new CustomerRepositoryImpl(session);
    }
    public Customer addCustomer(Customer customer) {
        if (!checkValidation.isValid(customer)) return new Customer();
        Transaction transaction = session.getTransaction();
        customerRepository.findByEmail(customer.getEmail()).ifPresentOrElse(
                tempCustomer -> {
                }, () -> {
                    try {
                        transaction.begin();
                        customerRepository.save(customer);
                        transaction.commit();
                    } catch (TransactionException e) {
                        System.out.println(e);
                        transaction.rollback();

                    }
                });
        return customer;
    }
    public Customer updateCustomer(Customer customer) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            customerRepository.update(customer);
            transaction.commit();
        } catch (TransactionException e) {
            transaction.rollback();
        } finally {
            customerRepository.getSession().close();

        }
        return customer;
    }

    @Override
    public Optional<Customer> loginByEmailAndPassword(String email, String password) {
      if(checkValidation.isValidEmail(email)&&checkValidation.isValidPassword(password)) {
      customerRepository.findByEmailAndPassword(email,password).ifPresentOrElse(
              customer->{
                  CheckValidation.memberTypeCustomer=customer;
              }
              ,()-> System.out.println("user not found")
      );
      }

        return Optional.empty();
    }

    public Customer removeCustomer(Customer customer) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            customerRepository.remove(customer);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            customerRepository.getSession().close();

        }
        return customer;
    }

    public Collection<Customer> load() {
        return customerRepository.load();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Session getSession() {
        return session;
    }
}
