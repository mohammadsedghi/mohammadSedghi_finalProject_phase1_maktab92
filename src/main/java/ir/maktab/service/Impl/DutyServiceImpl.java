package ir.maktab.service.Impl;
import ir.maktab.entity.Duty;
import ir.maktab.repository.DutyRepository;
import ir.maktab.repository.Impl.DutyRepositoryImpl;
import ir.maktab.service.DutyService;
import ir.maktab.util.validation.CheckValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

public class DutyServiceImpl implements DutyService {
    private DutyRepository dutyRepository;
    private Session session;

    CheckValidation checkValidation=new CheckValidation();
    public DutyServiceImpl(Session session) {
        this.session = session;

        dutyRepository=new DutyRepositoryImpl(session);
    }
    public Duty addDuty(Duty duty) {
         Transaction transaction=session.getTransaction();
         if (!checkValidation.isValid(duty)){return new Duty();}
        try {
            transaction.begin();
            dutyRepository.save(duty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return duty;
    }


    public Duty update(Duty duty) {
        Transaction transaction=session.getTransaction();
        try {
            transaction.begin();
            dutyRepository.update(duty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return duty;
    }


    public Duty remove(Duty duty) {
        Transaction transaction=session.getTransaction();
        try {
            transaction.begin();
            dutyRepository.remove(duty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

        return duty;
    }

    public Collection<Duty> load() {
        return dutyRepository.load();
    }

    public Optional<Duty> findById(Long id) {
        return dutyRepository.findById(id);
    }



}
