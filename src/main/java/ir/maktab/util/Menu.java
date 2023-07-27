package ir.maktab.util;

import ir.maktab.base.repository.util.HibernateUtil;
import ir.maktab.entity.Duty;
import ir.maktab.entity.Specialist;
import ir.maktab.entity.SubDuty;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.service.Impl.*;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Session session = HibernateUtil.getSessionFactory().openSession();
    CustomerServiceImpl customerService = new CustomerServiceImpl(session);
    SpecialistServiceImpl specialistService = new SpecialistServiceImpl(session);
    DutyServiceImpl dutyService = new DutyServiceImpl(session);
    SubDutyServiceImpl subDutyService = new SubDutyServiceImpl(session);
    WalletServiceImpl walletService = new WalletServiceImpl(session);
    // String imagePath = "src/main/java/ir/maktab/util/images/2.jpg";

    public void logIn() {
        System.out.println("=========login==========");
        System.out.println("inter type: 1)customer or 2)specialist");
        if (scanner.nextInt() == 1) {
            System.out.println("email: ");
            String email = scanner.next();
            System.out.println("password: ");
            String password = scanner.next();
            customerService.loginByEmailAndPassword(email, password);
        } else if (scanner.nextInt() == 2) {
            System.out.println("email: ");
            String email = scanner.next();
            System.out.println("password: ");
            String password = scanner.next();
            specialistService.loginByEmailAndPassword(email, password);
        } else throw new CustomException("user not found");
    }

    public void adminMenu() {
        System.out.println("---------------------------------------");
        System.out.println("1)list of All duty");
        System.out.println("2)add duty");
        System.out.println("3)add subDuty");
        System.out.println("4)signUp Specialist");
        System.out.println("5)confirm new specialists");
        System.out.println("6)add specialist to subDuty");
        System.out.println("7)remove specialist");
        System.out.println("---------------------------------------");
    }

    public void runAdminMenu() {
        while (true) {
            adminMenu();
            switch (scanner.nextInt()) {
                case 1:
                    Set<Duty> duties = new HashSet<>(dutyService.load());
                    for (Duty duty : duties
                    ) {
                        System.out.println(duty);
                    }
                    break;
                case 2:
                    System.out.println("inter name of duty:");
                    Duty duty = new Duty(scanner.next());
                    dutyService.addDuty(duty);
                    break;
                case 3:
                    Set<Duty> dutiesList = new HashSet<>(dutyService.load());
                    System.out.println("inter name of SubDuty:");
                    String nameOfSubDuty=scanner.next();
                    System.out.println("inter basePrice of SubDuty:");
                    Double basePrice=scanner.nextDouble();
                    System.out.println("inter description of SubDuty:");
                    String description=scanner.next();
                    for (Duty dutyCandidate : dutiesList
                    ) {
                        System.out.println(dutyCandidate);
                        System.out.println("1)selected 2)next duty");
                        if (scanner.nextInt() == 1) {
                            SubDuty subDuty = new SubDuty(dutyCandidate, nameOfSubDuty, basePrice, description);
                            Set<SubDuty> subDuties = new HashSet<>();
                            subDuties.add(subDuty);
                            dutyCandidate.setSubDuties(subDuties);
                            subDutyService.addSubDuty(subDuty);
                            dutyService.update(dutyCandidate);
                            break;
                        } else if (scanner.nextInt() == 2) {
                            System.out.println();
                        } else System.out.println("number is not valid");
                    }

                    break;
                case 4:
                    System.out.println("firstName");
                    String firstName = scanner.next();
                    System.out.println("lastname");
                    String lastName = scanner.next();
                    System.out.println("nationalId");
                    String nationalId = scanner.next();
                    System.out.println("email");
                    String email = scanner.next();
                    System.out.println("password");
                    String password = scanner.next();
                    System.out.println("imagePath");
                    String imagePath = scanner.next();
                    Set<Duty> Duties = new HashSet<>(dutyService.load());
                    Duty candidateduty = new Duty();
                    for (Duty duty1 : Duties
                    ) {
                        System.out.println("1)selected 2)anotherDuty");
                        if (scanner.nextInt() == 1) {
                            candidateduty = duty1;
                            break;
                        } else if (scanner.nextInt() == 2) {
                            System.out.println();
                        }
                    }
                    Set<SubDuty> subDuties = new HashSet<>(subDutyService.showAllSubDutyOfDuty(candidateduty));
                    Set<SubDuty> candidateSetOfSubDuties = new HashSet<>();
                    for (SubDuty subDuty : subDuties) {
                        System.out.println("1)selected 2)anotherSubDuty");
                        if (scanner.nextInt() == 1) {
                            candidateSetOfSubDuties.add(subDuty);
                        } else if (scanner.nextInt() == 2) {
                            System.out.println();
                        }
                    }
                        try {
                            Specialist specialist = Specialist.builder()
                                    .firstName(firstName)
                                    .lastname(lastName)
                                    .nationalId(nationalId)
                                    .email(email)
                                    .password(password)
                                    .registerDate(LocalDate.now())
                                    .registerTime(LocalTime.now())
                                    .status(SpecialistRegisterStatus.CONFIRM)
                                    .subDuties(candidateSetOfSubDuties)
                                    .wallet(walletService.createWallet())
                                    .score(0)
                                    .imageData(specialistService.convertImageToImageData(imagePath))
                                    .build();
                            specialistService.addSpecialist(specialist);
                        } catch (IOException e) {
                            //TODO SOMETHING
                            }
                        break;
                case 5:
                    specialistService.confirmSpecialistByAdmin();
                    break;
                case 6:

                    specialistService.addSpecialistToSubDuty();
                    break;
                case 7:
                   Set<Specialist> specialistSetCandidateForRemove=new HashSet<>(specialistService.load()) ;
                    for (Specialist specialist:specialistSetCandidateForRemove
                         ) {
                        System.out.println(specialist);
                        System.out.println("--------------------------------------");
                        System.out.println("1)select  2)next specialist");
                        if(scanner.nextInt()==1){
                            Set<SubDuty> subDuties1 = specialist.getSubDuties();
                            Set<SubDuty> copySubDuties1 = specialist.getSubDuties();
                            for (SubDuty subDuty :subDuties1
                                 ) {
                                System.out.println(subDuty);
                                System.out.println("1)select  2)next specialist");
                                if(scanner.nextInt()==1) {
                                   copySubDuties1.remove(subDuty);
                                } else if (scanner.nextInt()==2) {
                                    System.out.println();
                                }else System.out.println();
                            }
                            specialist.setSubDuties(copySubDuties1);
                            specialistService.update(specialist);
                        }else if(scanner.nextInt()==2) {
                            System.out.println();
                        }else System.out.println("you inter wrong number");
                    }
                    break;
            }
     }
  }


     //   public void customerMenu () {}


        public void specialistMenu () {
        switch (scanner.nextInt()){
            case 1:
                System.out.println("firstName");
                String firstName = scanner.next();
                System.out.println("lastname");
                String lastName = scanner.next();
                System.out.println("nationalId");
                String nationalId = scanner.next();
                System.out.println("email");
                String email = scanner.next();
                System.out.println("password");
                String password = scanner.next();
                System.out.println("imagePath");
                String imagePath = scanner.next();
                Set<Duty> Duties = new HashSet<>(dutyService.load());
                Duty candidateduty = new Duty();
                for (Duty duty1 : Duties
                ) {
                    System.out.println("1)selected 2)anotherDuty");
                    if (scanner.nextInt() == 1) {
                        candidateduty = duty1;
                        break;
                    } else if (scanner.nextInt() == 2) {
                        System.out.println();
                    }
                }
                Set<SubDuty> subDuties = new HashSet<>(subDutyService.showAllSubDutyOfDuty(candidateduty));
                Set<SubDuty> candidateSetOfSubDuties = new HashSet<>();
                for (SubDuty subDuty : subDuties) {
                    System.out.println("1)selected 2)anotherSubDuty");
                    if (scanner.nextInt() == 1) {
                        candidateSetOfSubDuties.add(subDuty);
                    } else if (scanner.nextInt() == 2) {
                        System.out.println();
                    }
                }
                try {
                    Specialist specialist = Specialist.builder()
                            .firstName(firstName)
                            .lastname(lastName)
                            .nationalId(nationalId)
                            .email(email)
                            .password(password)
                            .registerDate(LocalDate.now())
                            .registerTime(LocalTime.now())
                            .status(SpecialistRegisterStatus.NEW_SPECIALIST)
                            .subDuties(candidateSetOfSubDuties)
                            .wallet(walletService.createWallet())
                            .score(0)
                            .imageData(specialistService.convertImageToImageData(imagePath))
                            .build();
                    specialistService.addSpecialist(specialist);
                } catch (IOException e) {
                    //TODO SOMETHING
                }
                break;
        }
        }
    }

