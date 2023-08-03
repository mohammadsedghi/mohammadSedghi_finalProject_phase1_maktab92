package ir.maktab.service.Impl;



import ir.maktab.entity.CustomerComments;
import ir.maktab.repository.CustomerCommentsRepository;
import ir.maktab.repository.Impl.CustomerCommentsRepositoryImpl;
import ir.maktab.service.CustomerCommentsService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.util.Collection;
import java.util.Optional;

/**
 * this class design for CustomerComments instance and all thing that related with CustomerComments.
 *  * Crud method is implemented
 *  * and other required method that CustomerCommentsRepository to occur something(read,write)in database
 */
public class CustomerCommentsServiceImpl implements CustomerCommentsService {
   private CustomerCommentsRepository customerCommentsRepository;
   private Session session;
Transaction transaction;
    public CustomerCommentsServiceImpl(Session session) {
        this.session = session;
        transaction=session.getTransaction();
        customerCommentsRepository = new CustomerCommentsRepositoryImpl(session);

    }


    public CustomerComments save(CustomerComments customerComments) {
        try {
            transaction.begin();
            customerCommentsRepository.save(customerComments);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return customerComments;
    }


    public CustomerComments update(CustomerComments customerComments) {
        try {
            transaction.begin();
            customerCommentsRepository.update(customerComments);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return customerComments;
    }


    public CustomerComments remove(CustomerComments customerComments) {
        try {
            transaction.begin();
            customerCommentsRepository.remove(customerComments);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return customerComments;
    }


    public Collection<CustomerComments> load() {
        return customerCommentsRepository.load();
    }


    public Optional<CustomerComments> findById(Long id) {
        return customerCommentsRepository.findById(id);
    }

}
