package ir.maktab.service.Impl;


import ir.maktab.entity.Orders;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SpecialistSuggestion;
import ir.maktab.repository.Impl.SpecialistSuggestionRepositoryImpl;
import ir.maktab.repository.SpecialistSuggestionRepository;
import ir.maktab.service.SpecialistSuggestionService;
import ir.maktab.custom_exception.CustomDuplicateInfoException;
import ir.maktab.veiw.Menu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

/**
 * this class design for work with SpecialistSuggestion instance and all thing that related with SpecialistSuggestion.
 * Crud method is implemented
 *  and other required method that use SpecialistSuggestionRepository to occur something(read,write)in database
 *
 */
public class SpecialistSuggestionServiceImpl implements SpecialistSuggestionService {
    private final Session session;
    private  Transaction transaction;
    private SpecialistSuggestionRepository specialistSuggestionRepository;

    public SpecialistSuggestionServiceImpl(Session session) {
        this.session = session;
        transaction = session.getTransaction();
        specialistSuggestionRepository = new SpecialistSuggestionRepositoryImpl(session);
    }


    public SpecialistSuggestion save(SpecialistSuggestion specialistSuggestion) {
        try {
            transaction.begin();
            specialistSuggestionRepository.save(specialistSuggestion);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return specialistSuggestion;
    }


    public SpecialistSuggestion update(SpecialistSuggestion specialistSuggestion) {
        try {
            transaction.begin();
            specialistSuggestionRepository.update(specialistSuggestion);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return specialistSuggestion;
    }


    public SpecialistSuggestion remove(SpecialistSuggestion specialistSuggestion) {
        try {
            transaction.begin();
            specialistSuggestionRepository.remove(specialistSuggestion);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return specialistSuggestion;
    }


    public Collection<SpecialistSuggestion> load() {
        return specialistSuggestionRepository.load();
    }


    public Optional<SpecialistSuggestion> findById(Long id) {
        return specialistSuggestionRepository.findById(id);
    }


    @Override
    public SpecialistSuggestion submitSpecialistSuggestion(SpecialistSuggestion specialistSuggestion) {
        try {
            transaction.begin();

            specialistSuggestionRepository.save(specialistSuggestion);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return specialistSuggestion;
    }

    @Override
    public void findSuggestWithThisSpecialistAndOrder(Specialist specialist, Orders orders) {
        Menu menu = new Menu();
        specialistSuggestionRepository.findSuggestWithThisSpecialistAndOrder(specialist, orders).ifPresent(
                specialistSuggestion -> {
                    try {
                        throw new CustomDuplicateInfoException("this specialist submit suggest for this order");
                    } catch (CustomDuplicateInfoException cdi) {
                        System.out.println(cdi.getMessage());
                        menu.customerMenu();
                    }
                });
    }
}
