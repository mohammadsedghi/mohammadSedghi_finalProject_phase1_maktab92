package ir.maktab.service.Impl;


import ir.maktab.entity.Duty;
import ir.maktab.entity.SubDuty;
import ir.maktab.repository.Impl.SubDutyRepositoryImpl;
import ir.maktab.repository.SubDutyRepository;
import ir.maktab.service.SubDutyService;
import ir.maktab.util.validation.CheckValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

public class SubDutyServiceImpl implements SubDutyService {
private Session session;
private SubDutyRepository subDutyRepository;
CheckValidation checkValidation=new CheckValidation();
    public SubDutyServiceImpl(Session session) {
        this.session = session;
        subDutyRepository=new SubDutyRepositoryImpl(session);
    }

    public SubDuty addSubDuty(SubDuty subDuty) {
        Transaction transaction=session.getTransaction();
        if (!checkValidation.isValid(subDuty)){return new SubDuty();}
          try {
            transaction.begin();
            subDutyRepository.save(subDuty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return subDuty;
    }

    @Override
    public Collection<SubDuty> showAllSubDutyOfDuty(Duty duty) {
        return subDutyRepository.showSubDutyOfDuty(duty);
    }


    public SubDuty update(SubDuty subDuty) {
        Transaction transaction=session.getTransaction();
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

    public SubDuty remove(SubDuty subDuty) {
        Transaction transaction=session.getTransaction();
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


    public Collection<SubDuty> load() {
        return subDutyRepository.load();
    }

    public Optional<SubDuty> findById(Long id) {
        return subDutyRepository.findById(id);
    }

    @Override
    public boolean editSubDutyPrice(SubDuty subduty, Double price) {
        Transaction transaction= session.getTransaction();
        subduty.setBasePrice(price);
        try {
            transaction.begin();
            subDutyRepository.update(subduty);
            transaction.commit();
            return true;
        }catch (TransactionException t){
            System.out.println(t.getMessage());
        }

        return false;
    }

    @Override
    public boolean editSubDutyDescription(SubDuty subduty, String description) {
        Transaction transaction= session.getTransaction();
        subduty.setDescription(description);
        try {
            transaction.begin();
            subDutyRepository.update(subduty);
            transaction.commit();
            return true;
        }catch (TransactionException t){
            System.out.println(t.getMessage());
        }
        return false;
    }
}