package ir.maktab;

import ir.maktab.base.repository.util.HibernateUtil;
import ir.maktab.entity.*;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.service.Impl.*;
import ir.maktab.util.Menu;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
     //   Session session = HibernateUtil.getSessionFactory().openSession();
//        AddressServiceImpl addressService=new AddressServiceImpl(session);
//        Address address=new Address("a","a","a","1234567891",12);
//        addressService.save(address);

       // String imagePath = "/ir/maktab/util/images/2.jpg";
       // String imagePath = "src/main/java/ir/maktab/2.jpg";
Menu menu=new Menu();
menu.firstMenu();
    }
}