package ir.maktab.service.Impl;


import ir.maktab.entity.SubDuty;
import ir.maktab.repository.Impl.SubDutyRepositoryImpl;
import ir.maktab.repository.SubDutyRepository;
import ir.maktab.service.SubDutyService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

public class SubDutyServiceImpl implements SubDutyService,SubDutyRepository {
private Session session;
private Transaction transaction;
private SubDutyRepository subDutyRepository;

    public SubDutyServiceImpl(Session session) {
        this.session = session;
        transaction=session.getTransaction();
        subDutyRepository=new SubDutyRepositoryImpl(session);
    }

    @Override
    public SubDuty save(SubDuty subDuty) {
          try {
            transaction.begin();
            subDutyRepository.save(subDuty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            subDutyRepository.getSession().close();
        }
        return subDuty;
    }

    @Override
    public SubDuty update(SubDuty subDuty) {
        try {
            transaction.begin();
            subDutyRepository.update(subDuty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            subDutyRepository.getSession().close();
        }
        return subDuty;
    }

    @Override
    public SubDuty remove(SubDuty subDuty) {
        try {
            transaction.begin();
            subDutyRepository.remove(subDuty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            subDutyRepository.getSession().close();
        }
        return subDuty;
    }

    @Override
    public Collection<SubDuty> load() {
        return subDutyRepository.load();
    }

    @Override
    public Optional<SubDuty> findById(Long id) {
        return subDutyRepository.findById(id);
    }

    @Override
    public Session getSession() {
        return session;
    }
}