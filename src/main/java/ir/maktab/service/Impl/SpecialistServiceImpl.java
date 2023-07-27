package ir.maktab.service.Impl;



import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.repository.Impl.SpecialistRepositoryImpl;
import ir.maktab.repository.SpecialistRepository;
import ir.maktab.service.SpecialistService;
import ir.maktab.util.CheckValidation;
import ir.maktab.util.CustomException;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SpecialistServiceImpl  implements SpecialistService {
    private Session session;
     SpecialistRepository specialistRepository;
    CheckValidation checkValidation=new CheckValidation();
    Transaction transaction = session.getTransaction();

    public SpecialistServiceImpl(Session session) {
        this.session = session;
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

    @Override
    public Optional<Specialist> loginByEmailAndPassword(String email, String password) {
        if(checkValidation.isValidEmail(email)&&checkValidation.isValidPassword(password)) {
            specialistRepository.findByEmailAndPassword(email,password).ifPresentOrElse(
                    specialist->{
                        CheckValidation.memberTypespecialist=specialist;
                    }
                    ,()-> System.out.println("user not found")
            );
        }

        return Optional.empty();

    }

    @Override
    public void confirmSpecialistByAdmin() {
        Scanner scanner=new Scanner(System.in);
       Set<Specialist> unConfirmSpecialist=new HashSet<>(specialistRepository.showUnConfirmSpecialist());
    if(unConfirmSpecialist.size()==0){

    }else{
        for (Specialist specialist:unConfirmSpecialist
             ) {
            System.out.println(specialist);
            System.out.println("1)confirm this specialist 2)no");
            if(scanner.nextInt()==1){
                specialist.setStatus(SpecialistRegisterStatus.CONFIRM);
                Transaction transaction= session.getTransaction();
                try {
                    transaction.begin();
                    specialistRepository.update(specialist);
                    transaction.commit();
                } catch (TransactionException e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
            }
        }
    }
    }

    public String convertImageToImageData(String imagePath)throws IOException {
    try {
        byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
        checkValidation.isJpgImage(fileContent);
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
    @Override
    public Boolean addSpecialistToSubDuty(Specialist specialist,SubDuty subDuty) {
       Set<SubDuty> setOfSubDuty=specialist.getSubDuties();
        for (SubDuty subDutyCandidate:setOfSubDuty
             ) {
           if (subDutyCandidate!=subDuty){
               setOfSubDuty.add(subDuty);
               specialist.setSubDuties(setOfSubDuty);
               update(specialist);
               return true;
           }else {
             throw new CustomException("this specialist added to this subDuty before");
               }
           }
        return false;
        }



}
