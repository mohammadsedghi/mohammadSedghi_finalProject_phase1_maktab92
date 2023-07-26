package ir.maktab.service.Impl;
import ir.maktab.entity.Duty;
import ir.maktab.repository.DutyRepository;
import ir.maktab.repository.Impl.DutyRepositoryImpl;
import ir.maktab.service.DutyService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

public class DutyServiceImpl implements DutyService,DutyRepository {
    private DutyRepository dutyRepository;
    private Session session;
    private Transaction transaction;

    public DutyServiceImpl(Session session) {
        this.session = session;
        transaction=session.getTransaction();
        dutyRepository=new DutyRepositoryImpl(session);
    }

    @Override
    public Duty save(Duty duty) {
        try {
            transaction.begin();
            dutyRepository.save(duty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            dutyRepository.getSession().close();

        }
        return duty;
    }

    @Override
    public Duty update(Duty duty) {
        try {
            transaction.begin();
            dutyRepository.update(duty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            dutyRepository.getSession().close();

        }
        return duty;
    }

    @Override
    public Duty remove(Duty duty) {
        try {
            transaction.begin();
            dutyRepository.remove(duty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            dutyRepository.getSession().close();

        }
        return duty;
    }

    @Override
    public Collection<Duty> load() {
        return dutyRepository.load();
    }

    @Override
    public Optional<Duty> findById(Long id) {
        return dutyRepository.findById(id);
    }

    @Override
    public Session getSession() {
        return session;
    }
}
