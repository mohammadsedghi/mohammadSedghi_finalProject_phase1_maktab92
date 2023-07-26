package ir.maktab.service.Impl;



import ir.maktab.entity.Specialist;
import ir.maktab.repository.Impl.SpecialistRepositoryImpl;
import ir.maktab.repository.SpecialistRepository;
import ir.maktab.service.SpecialistService;
import ir.maktab.util.CheckValidation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Optional;

public class SpecialistServiceImpl  implements SpecialistService {
    private Session session;
    private Transaction transaction;
    private SpecialistRepository specialistRepository;
    CheckValidation checkValidation=new CheckValidation();

    public SpecialistServiceImpl(Session session) {
        this.session = session;
        transaction= session.getTransaction();
        specialistRepository=new SpecialistRepositoryImpl(session);
    }

    public Specialist update(Specialist specialist) {
        try {
            transaction.begin();
            specialistRepository.update(specialist);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            specialistRepository.getSession().close();
        }
        return specialist;
    }


    public Specialist remove(Specialist specialist) {
        try {
            transaction.begin();
            specialistRepository.remove(specialist);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            specialistRepository.getSession().close();
        }
        return specialist;
    }


    public Collection<Specialist> load() {
        return specialistRepository.load();
    }


    public Optional<Specialist> findById(Long id) {
        return specialistRepository.findById(id);
    }



    @Override
    public Specialist addSpecialist(Specialist specialist) {
        if (!checkValidation.isValid(specialist)) return new Specialist();
        Transaction transaction = session.getTransaction();
        specialistRepository.findByEmail(specialist.getEmail()).ifPresentOrElse(
                tempCustomer -> {
                }, () -> {
                    try {
                        transaction.begin();
                        specialistRepository.save(specialist);
                        transaction.commit();
                    } catch (TransactionException e) {
                        System.out.println(e);
                        transaction.rollback();

                    }
                });
        return specialist;
    }
public byte[] convertImageToImageDats(String imagePath)throws IOException {
    File imageFile = new File(imagePath);
    return Files.readAllBytes(imageFile.toPath());
}
}
