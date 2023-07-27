package ir.maktab.service.Impl;



import ir.maktab.entity.Specialist;
import ir.maktab.repository.Impl.SpecialistRepositoryImpl;
import ir.maktab.repository.SpecialistRepository;
import ir.maktab.service.SpecialistService;
import ir.maktab.util.CheckValidation;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
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
public String convertImageToImageData(String imagePath)throws IOException {
    try {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return encodedString;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}
    public  void convertByteArrayToImage(Specialist specialist) throws IOException {
        Optional<Specialist> SpecialistId = specialistRepository.findById(specialist.getId());
       SpecialistId.ifPresentOrElse(member->{
           byte[] imageData = Base64.getDecoder().decode(member.getImageData());
           String newFilePath = "t.jpg";
           try (FileOutputStream fileOutputStream = new FileOutputStream(newFilePath)) {
               fileOutputStream.write(imageData);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }, Specialist::new
           );
    }


}
