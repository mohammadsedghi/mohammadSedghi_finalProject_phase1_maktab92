package ir.maktab.service.Impl;



import ir.maktab.entity.Duty;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.repository.Impl.SpecialistRepositoryImpl;
import ir.maktab.repository.SpecialistRepository;
import ir.maktab.service.SpecialistService;
import ir.maktab.util.Menu;
import ir.maktab.util.custom_exception.CustomInputOutputException;
import ir.maktab.util.custom_exception.CustomNumberFormatException;
import ir.maktab.util.hash_password.EncryptPassword;
import ir.maktab.util.validation.CheckValidation;
import ir.maktab.util.custom_exception.CustomException;
import ir.maktab.util.custom_exception.CustomNoResultException;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class SpecialistServiceImpl  implements SpecialistService {
    private final Session session;
     SpecialistRepository specialistRepository;
    CheckValidation checkValidation=new CheckValidation();

    public SpecialistServiceImpl(Session session) {
        this.session = session;
        specialistRepository=new SpecialistRepositoryImpl(session);
    }
    public Specialist update(Specialist specialist) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            specialistRepository.update(specialist);
            transaction.commit();
        } catch (TransactionException e) {
            transaction.rollback();
        }
        return specialist;
    }
    public Specialist remove(Specialist specialist) {
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            specialistRepository.remove(specialist);
            transaction.commit();
        } catch (TransactionException e) {
            transaction.rollback();
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
        try {
            Transaction transaction = session.getTransaction();
            if (!checkValidation.isValid(specialist))throw new CustomException("input  is invalid  ");
            specialistRepository.findByEmail(specialist.getEmail()).ifPresentOrElse(
                    tempCustomer -> {throw new CustomException("Specialist with this email and password is exist ");
                    }, () -> {
                        try {
                            transaction.begin();
                            specialist.setPassword(encryptSpecialistPassword(specialist.getPassword()));
                            specialistRepository.save(specialist);
                            transaction.commit();
                        } catch (TransactionException e) {
                            System.out.println(e.getMessage());
                            transaction.rollback();
                        }
                    });
        }catch (CustomNoResultException c){
            System.out.println(c.getMessage());
        }
        return specialist;
    }

    @Override
    public Optional<Specialist> loginByEmailAndPassword(String email, String password) {
        Menu menu=new Menu();
        try {
            if (checkValidation.isValidEmail(email) && checkValidation.isValidPassword(password)) {
                specialistRepository.findByEmailAndPassword(email, encryptSpecialistPassword(password)).ifPresentOrElse(
                        specialist -> {
                            CheckValidation.memberTypespecialist = specialist;
                                if (CheckValidation.memberTypespecialist.getStatus()==SpecialistRegisterStatus.WAITING_FOR_CONFIRM){
                                    throw new CustomNoResultException("you cannot access before admin confirmed you");
                                }else menu.runSpecialistMenu();
                        }
                        , () -> {
                            throw new CustomNoResultException("Specialist not found");
                        }
                );
            }else {throw new CustomNoResultException("you inter invalid input for login");}
        }catch (CustomNoResultException c) {
            CheckValidation.memberTypespecialist =new Specialist();
            System.out.println(c.getMessage());
            menu.firstMenu();
        }
        return Optional.empty();
    }

    @Override
    public void confirmSpecialistByAdmin() {
        Scanner scanner=new Scanner(System.in);
       Set<Specialist> unConfirmSpecialist=new HashSet<>(specialistRepository.showUnConfirmSpecialist());
    if(unConfirmSpecialist.size()==0){
        System.out.println("no specialist unConfirm found");
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
                    transaction.rollback();
                }
            }
        }
    }
    }

    public String convertImageToImageData(String imagePath) throws CustomInputOutputException {
    try { if (!checkValidation.isJpgImage(imagePath))throw new CustomInputOutputException("image file format is not valid121212");

        byte[] fileContent = FileUtils.readFileToByteArray(new File(imagePath));
    if (!checkValidation.isJpgImage(fileContent))throw new CustomInputOutputException("image file format is not valid");
   if(!checkValidation.isImageHaveValidSize(fileContent)){throw new CustomInputOutputException("image size must be lower than 300KB");}
        return Base64.getEncoder().encodeToString(fileContent);
    } catch ( IOException|CustomInputOutputException e) {
        System.out.println(e.getMessage());
        throw new CustomInputOutputException("image size must be lower than 300KB");
    }

}
    public  void convertByteArrayToImage(Specialist specialist,String newFilePath ){
            Optional<Specialist> SpecialistId = specialistRepository.findById(specialist.getId());
            SpecialistId.ifPresentOrElse(member -> {
                        byte[] imageData = Base64.getDecoder().decode(member.getImageData());
                        try (FileOutputStream fileOutputStream = new FileOutputStream(newFilePath)) {
                            fileOutputStream.write(imageData);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
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

    @Override
    public boolean changePassword(String email,String oldPassword,String newPassword) {
        Transaction transaction=session.getTransaction();
        try {
            if (checkValidation.isValidEmail(email) && checkValidation.isValidPassword(oldPassword)) {
                if (checkValidation.isValidPassword(newPassword)) {
                    specialistRepository.findByEmailAndPassword(email, encryptSpecialistPassword(oldPassword)).ifPresentOrElse(
                            specialist -> {
                                specialist.setPassword(encryptSpecialistPassword(newPassword));
                                try {
                                    transaction.begin();
                                    specialistRepository.update(specialist);
                                    transaction.commit();
                                }catch (TransactionException t){
                                    transaction.rollback();
                                }
                            }, () -> {
                                throw new CustomNoResultException("this user is not found");
                            }
                    );
                } else {
                    throw new CustomNoResultException("new password is invalid");
                }
            } else {
                throw new CustomNoResultException("email and old password is invalid");
            }
        }catch (CustomNoResultException c){
            System.out.println(c.getMessage());
            return false;
        }
        return true;

    }



    @Override
    public void removeSpecialistFromDuty() {
        Scanner scanner=new Scanner(System.in);
        Set<Specialist> confirmSpecialist=new HashSet<>(specialistRepository.showConfirmSpecialist());
        if(confirmSpecialist.size()==0){
            System.out.println("no specialist unConfirm found");
        }else{
            for (Specialist specialist:confirmSpecialist
            ) {
                System.out.println(specialist);
                System.out.println("1)unConfirm(remove) this specialist 2)no");
                if(scanner.nextInt()==1){
                    specialist.setStatus(SpecialistRegisterStatus.WAITING_FOR_CONFIRM);
                    Transaction transaction= session.getTransaction();
                    try {
                        transaction.begin();
                        specialistRepository.update(specialist);
                        transaction.commit();
                    } catch (TransactionException e) {
                        transaction.rollback();
                    }
                }
            }
        }
    }

    @Override
    public String encryptSpecialistPassword(String password) {
        EncryptPassword encryptPassword=new EncryptPassword();
        return encryptPassword.hashPassword(password);
    }


}
