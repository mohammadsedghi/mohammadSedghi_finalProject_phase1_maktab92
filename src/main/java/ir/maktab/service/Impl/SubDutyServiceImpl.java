package ir.maktab.service.Impl;


import ir.maktab.entity.Duty;
import ir.maktab.entity.SubDuty;
import ir.maktab.repository.Impl.SubDutyRepositoryImpl;
import ir.maktab.repository.SubDutyRepository;
import ir.maktab.service.SubDutyService;
import ir.maktab.veiw.Menu;
import ir.maktab.custom_exception.CustomException;
import ir.maktab.custom_exception.CustomNumberFormatException;
import ir.maktab.util.validation.CheckValidation;
import ir.maktab.util.validation.CustomRegex;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.util.Collection;
import java.util.Optional;

/**
 * this class design for work with SubDuty instance and all thing that related with SubDuty.
 *   Crud method is implemented
 *   and other required method that use SubDutyRepository to occur something(read,write)in database
 *
 */
public class SubDutyServiceImpl implements SubDutyService {
    private Session session;
    private SubDutyRepository subDutyRepository;
    CheckValidation checkValidation = new CheckValidation();

    public SubDutyServiceImpl(Session session) {
        this.session = session;
        subDutyRepository = new SubDutyRepositoryImpl(session);
    }

    public SubDuty addSubDuty(SubDuty subDuty) {
        Transaction transaction = session.getTransaction();
        if (!checkValidation.isValid(subDuty)) {
            return new SubDuty();
        }
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
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            subDutyRepository.update(subDuty);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return subDuty;
    }

    public SubDuty remove(SubDuty subDuty) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            subDutyRepository.remove(subDuty);
            transaction.commit();
        } catch (TransactionException e) {
            transaction.rollback();
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
    public boolean editSubDutyPrice(SubDuty subduty, String price) {
        Transaction transaction = session.getTransaction();
        CustomRegex customRegex = new CustomRegex();
        try {
            if (customRegex.checkOneInputIsValid(price, customRegex.getValidPrice())) {
                subduty.setBasePrice(Double.parseDouble(price));
                try {
                    transaction.begin();
                    subDutyRepository.update(subduty);
                    transaction.commit();
                    return true;
                } catch (TransactionException t) {
                    System.out.println(t.getMessage());
                }
            } else throw new CustomNumberFormatException("input basePrice is invalid");

        } catch (CustomNumberFormatException cnf) {
            System.out.println(cnf.getMessage());
        }
        return false;
    }

    @Override
    public boolean editSubDutyDescription(SubDuty subduty, String description) {
        Transaction transaction = session.getTransaction();
        CustomRegex customRegex = new CustomRegex();
        try {


            if (customRegex.checkOneInputIsValid(description, customRegex.getValidStr())) {
                subduty.setDescription(description);
                try {
                    transaction.begin();
                    subDutyRepository.update(subduty);
                    transaction.commit();
                    return true;
                } catch (TransactionException t) {
                    System.out.println(t.getMessage());
                }
            } else {
                throw new CustomException("input description is invalid");
            }
        } catch (CustomException c) {
            System.out.println(c.getMessage());
            throw new CustomException("input description is invalid");
        }
        return false;
    }

    @Override
    public boolean isExistSubDuty(String name) {
        Menu menu = new Menu();
        try {
            subDutyRepository.isExistSubDuty(name).ifPresent(
                    subDuty -> {
                        throw new CustomException("this subDuty is exist");
                    }
            );
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            menu.runAdminMenu();
            return true;
        }
        return false;
    }

}