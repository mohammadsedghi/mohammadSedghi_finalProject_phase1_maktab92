package ir.maktab;

import ir.maktab.base.repository.util.HibernateUtil;
import ir.maktab.entity.*;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.service.Impl.*;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        AddressServiceImpl addressService=new AddressServiceImpl(session);
//        Address address=new Address("a","a","a","1234567891",12);
//        addressService.save(address);
        CustomerServiceImpl customerService=new CustomerServiceImpl(session);
        SpecialistServiceImpl specialistService=new SpecialistServiceImpl(session);
        Wallet wallet=new Wallet(10d);
        Wallet wallet1=new Wallet(20d);
        WalletServiceImpl walletService=new WalletServiceImpl(session);
        walletService.save(wallet);
        walletService.save(wallet1);
//        Customer customer= Customer.builder()
//                .firstName("ali")
//                .Lastname("sedghi")
//                .nationalId("1234567891")
//                .Email("mohammad@gmail.com")
//                .password("aa123aa112")
//                .registerDate( LocalDate.now())
//                .registerTime(LocalTime.now())
//                .wallet(wallet)
//                .build();
//        customerService.addCustomer(customer);
        Duty duty=new Duty("b");
        SubDuty subDuty=new SubDuty(duty,"A",1200d,"a");
        Set<SubDuty> subDuties=new HashSet<>();
        subDuties.add(subDuty);
        duty.setSubDuties(subDuties);
        DutyServiceImpl dutyService=new DutyServiceImpl(session);
        SubDutyServiceImpl subDutyService=new SubDutyServiceImpl(session);
        dutyService.addDuty(duty);
        subDutyService.addSubDuty(subDuty);

       // String imagePath = "/ir/maktab/util/images/2.jpg";
       // String imagePath = "src/main/java/ir/maktab/2.jpg";
        String imagePath = "src/main/java/ir/maktab/util/images/2.jpg";
        Specialist specialist=Specialist.builder()
                .firstName("ali")
                .lastname("sedghi")
                .nationalId("1234567891")
                .email("mohammad@gmail.com")
                .password("aa123aa112")
                .registerDate( LocalDate.now())
                .registerTime(LocalTime.now())
                .status(SpecialistRegisterStatus.NEW_SPECIALIST)
                .subDuties(subDuties)
                .wallet(wallet1)
                .imageData(specialistService.convertImageToImageData(imagePath))
                .build();
        specialistService.addSpecialist(specialist);
        specialistService.convertByteArrayToImage(specialist);
    }
}