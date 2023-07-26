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
        } finally {
            customerCommentsRepository.getSession().close();

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
        } finally {
            customerCommentsRepository.getSession().close();

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
        } finally {
            customerCommentsRepository.getSession().close();

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
