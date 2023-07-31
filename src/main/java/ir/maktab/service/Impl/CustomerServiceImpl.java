package ir.maktab.service.Impl;


import ir.maktab.entity.Customer;
import ir.maktab.repository.CustomerRepository;
import ir.maktab.repository.Impl.CustomerRepositoryImpl;
import ir.maktab.service.CustomerService;
import ir.maktab.util.Menu;
import ir.maktab.util.custom_exception.CustomException;
import ir.maktab.util.custom_exception.CustomNoResultException;
import ir.maktab.util.hash_password.EncryptPassword;
import ir.maktab.util.validation.CheckValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.util.Collection;
import java.util.Optional;




public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Session session;
CheckValidation checkValidation=new CheckValidation();

    public CustomerServiceImpl(Session session) {
        this.session = session;
        customerRepository = new CustomerRepositoryImpl(session);
    }
    public Customer addCustomer(Customer customer) {
        Menu menu=new Menu();
        try{
        if (!checkValidation.isValid(customer)) throw new CustomException("input is invalid ");
        Transaction transaction = session.getTransaction();
        customerRepository.findByEmail(customer.getEmail()).ifPresentOrElse(
                tempCustomer -> {throw new CustomException("customer with this email and password is exist ");

                }, () -> {
                    try {
                        transaction.begin();
                        customer.setPassword(encryptCustomerPassword(customer.getPassword()));
                        customerRepository.save(customer);
                        transaction.commit();
                    } catch (TransactionException e) {
                        System.out.println(e.getMessage());
                        transaction.rollback();

                    }
                });
        return customer;
    }catch (CustomException c){
        System.out.println(c.getMessage());
        menu.firstMenu();
        return new Customer();
        }
    }
    public Customer updateCustomer(Customer customer) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            customerRepository.update(customer);
            transaction.commit();
        } catch (TransactionException e) {
            transaction.rollback();
        }
        return customer;
    }

    @Override
    public Optional<Customer> loginByEmailAndPassword(String email, String password) {
        Menu menu=new Menu();
        try {
            if (checkValidation.isValidEmail(email) && checkValidation.isValidPassword(password)) {
                customerRepository.findByEmailAndPassword(email, encryptCustomerPassword(password)).ifPresentOrElse(
                        customer -> {
                            CheckValidation.memberTypeCustomer = customer;
                            menu.runCustomerMenu();
                        }
                        , () ->{throw new CustomNoResultException("customer not found");}

                );
            }else {throw new CustomNoResultException("you inter invalid input for login");}
         }catch (CustomNoResultException c) {
        CheckValidation.memberTypeCustomer =new Customer();
        System.out.println(c.getMessage());
        menu.firstMenu();
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
            transaction.rollback();
        }
        return customer;
    }

    public Collection<Customer> load() {
        return customerRepository.load();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
@Override
    public boolean changePassword(String email,String oldPassword,String newPassword) {
        Transaction transaction=session.getTransaction();
        try {
            if (checkValidation.isValidEmail(email) && checkValidation.isValidPassword(oldPassword)) {
                if (checkValidation.isValidPassword(newPassword)) {
                    customerRepository.findByEmailAndPassword(email, encryptCustomerPassword(oldPassword)).ifPresentOrElse(
                            customer -> {
                                customer.setPassword(encryptCustomerPassword(newPassword));
                                try {
                                    transaction.begin();
                                    customerRepository.update(customer);
                                    transaction.commit();
                                }catch (TransactionException t){
                                    transaction.rollback();
                                }
                            }, () -> {
                                throw new CustomNoResultException("this customer is not found");
                            }
                    );
                } else {
                    throw new CustomNoResultException("new password is invalid");
                }
            } else {
                throw new CustomNoResultException("email and old password is invalid");
            }
        }catch (CustomNoResultException c){
            System.out.println(c.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public String encryptCustomerPassword(String password) {
        EncryptPassword encryptPassword=new EncryptPassword();
        return encryptPassword.hashPassword(password);
    }



}
