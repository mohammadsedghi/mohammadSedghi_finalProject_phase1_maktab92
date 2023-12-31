package ir.maktab.veiw;

import ir.maktab.base.repository.util.HibernateUtil;
import ir.maktab.entity.*;
import ir.maktab.entity.enumeration.OrderStatus;
import ir.maktab.entity.enumeration.SpecialistRegisterStatus;
import ir.maktab.service.Impl.*;
import ir.maktab.util.CalenderAndValidation;
import ir.maktab.custom_exception.CustomException;
import ir.maktab.custom_exception.CustomInputOutputException;
import ir.maktab.custom_exception.CustomNoResultException;
import ir.maktab.util.validation.CheckValidation;
import ir.maktab.util.validation.CustomRegex;
import org.hibernate.Session;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Main class designed for test all method that exist in all service of all entity
 * this class have method that show other service and method is how to work(Menu)
 * this program written with java language programming and jdk 19.
 * written in 8/4/2023 . programmer mohammad sedghi...
 */
public class Menu {
    Scanner scanner = new Scanner(System.in);
    Session session = HibernateUtil.getSessionFactory().openSession();
   private CustomerServiceImpl customerService = new CustomerServiceImpl(session);
    private AdminServiceImpl adminService = new AdminServiceImpl(session);
    private SpecialistServiceImpl specialistService = new SpecialistServiceImpl(session);
    private DutyServiceImpl dutyService = new DutyServiceImpl(session);
    private SubDutyServiceImpl subDutyService = new SubDutyServiceImpl(session);
    private   WalletServiceImpl walletService = new WalletServiceImpl(session);
    private AddressServiceImpl addressService = new AddressServiceImpl(session);
    private  OrderServiceImpl orderService = new OrderServiceImpl(session);
    private SpecialistSuggestionServiceImpl specialistSuggestionService = new SpecialistSuggestionServiceImpl(session);
    // String imagePath = "src/main/java/ir/maktab/util/images/2.jpg";

    public void firstMenu() {
        System.out.println("========welcome==========");
        System.out.println("1)login    2)signup specialist    3)signup customer");
        switch (scanner.nextInt()) {
            case 1 -> logIn();
            case 2 -> specialistSignup();
            case 3 -> customerSignup();
        }

    }


    public void logIn() {
        System.out.println("=========login==========");
        System.out.println("inter type: 1)admin   2)customer   or 3)specialist");
        int type = scanner.nextInt();
        System.out.println("email: ");
        String email = scanner.next();
        System.out.println("password: ");
        String password = scanner.next();
        System.out.println("-----------------------------------------------");
        switch (type) {
            case 1 -> adminService.loginByEmailAndPassword(email, password);
            case 2 -> customerService.loginByEmailAndPassword(email, password);
            case 3 -> specialistService.loginByEmailAndPassword(email, password);
            default -> {
                try {
                    throw new CustomNoResultException("you enter wrong number or user not found try again or signup");
                } catch (CustomNoResultException c) {
                    System.out.println(c.getMessage());
                    firstMenu();
                }
            }
        }
    }

    public void adminMenu() {
        System.out.println("---------------------------------------");
        System.out.println("1)list of All duty");
        System.out.println("2)list of All sub duty");
        System.out.println("3)add duty");
        System.out.println("4)add subDuty");
        System.out.println("5)signUp Specialist");
        System.out.println("6)confirm new specialists");
        System.out.println("7)add specialist to subDuty");
        System.out.println("8)remove specialist from SubDuty");
        System.out.println("9)remove specialist for both subDuty and duty");
        System.out.println("10)Edit description of subDuty");
        System.out.println("11)Edit price of subDuty");
        System.out.println("---------------------------------------");
        System.out.println("12)logout --------------------- 13)signup");
    }

    public void runAdminMenu() {
        while (true) {
            adminMenu();
            switch (scanner.nextInt()) {
                case 1 -> {
                    Set<Duty> duties = new HashSet<>(dutyService.load());
                    for (Duty duty : duties
                    ) {
                        System.out.println(duty);
                        System.out.println("----------------------------");
                    }
                }
                case 2 -> dutyService.load().forEach(duty -> {
                    System.out.println(duty);
                    System.out.println("----------------------------------");
                    System.out.println("1)selected    2)other subDuty");
                    if (scanner.nextInt() == 1) {
                        subDutyService.showAllSubDutyOfDuty(duty).forEach(System.out::println);
                        System.out.println("----------------------------------");
                    }
                });

                case 3 -> {
                    System.out.println("inter name of duty:");
                    Duty duty = new Duty(scanner.next());
                    dutyService.addDuty(duty);
                }
                case 4 -> {
                    Set<Duty> dutiesList = new HashSet<>(dutyService.load());
                    System.out.println("inter name of SubDuty:");
                    String nameOfSubDuty = scanner.next();
                    System.out.println("inter basePrice of SubDuty:");
                    subDutyService.isExistSubDuty(nameOfSubDuty);
                    double basePrice = scanner.nextDouble();
                    System.out.println("inter description of SubDuty:");
                    String description = scanner.next();
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
                }
                case 5 -> {
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
                    Duty selectedDuty;
                    for (Duty candidateDuty : Duties
                    ) {
                        System.out.println(candidateDuty);
                        System.out.println("1)selected 2)anotherDuty");
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                selectedDuty = candidateDuty;
                                Set<SubDuty> subDuties = new HashSet<>(selectedDuty.getSubDuties());
                                Set<SubDuty> candidateSetOfSubDuties = new HashSet<>();
                                for (SubDuty subDuty : subDuties) {
                                    System.out.println(subDuty);
                                    System.out.println("------------------------------");
                                    System.out.println("1)selected 2)anotherSubDuty");
                                    switch (scanner.nextInt()) {
                                        case 1 -> candidateSetOfSubDuties.add(subDuty);
                                        case 2 -> System.out.println();
                                        default -> System.out.println("inter wrong number ");
                                    }
                                }
                                Specialist specialist;
                                try {
                                    specialist = Specialist.builder()
                                            .duty(selectedDuty)
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
                                } catch (CustomInputOutputException e) {
                                    System.out.println(e.getMessage());
                                }

                            }
                            case 2 -> System.out.println();
                            default -> System.out.println(" inter wrong number");
                        }
                    }
                }
                case 6 -> specialistService.confirmSpecialistByAdmin();
                case 7 -> {
                    Set<Specialist> candidSpecialists = new HashSet<>(specialistService.load());
                    for (Specialist specialist : candidSpecialists
                    ) {
                        System.out.println(specialist);
                        System.out.println("-------------------------------------");
                        System.out.println("1)select  2)next specialist");
                        try {
                            switch (scanner.nextInt()) {
                                case 1 -> {
                                    Set<SubDuty> specialistSubDuties = specialist.getSubDuties();
                                    if (specialistSubDuties.size() == 0) {
                                        Set<SubDuty> subdutySet = specialist.getDuty().getSubDuties();
                                        if (subdutySet.size() == 0) System.out.println("this duty have empty sub Duty");
                                        for (SubDuty SubDuty : subdutySet) {
                                            System.out.println(SubDuty);
                                            System.out.println("-------------------------------------");
                                            System.out.println("1)select  2)next subDuty");
                                            switch (scanner.nextInt()) {
                                                case 1 -> {
                                                    // Set<SubDuty> subDuties1 = specialist.getSubDuties();
                                                    specialistSubDuties.add(SubDuty);
                                                    specialist.setSubDuties(specialistSubDuties);
                                                    specialistService.update(specialist);
                                                }
                                                case 2 -> System.out.println("--------------------------------------");
                                                default -> System.out.println("inter wrong number");
                                            }
                                        }
                                    } else {
                                        Duty temporaryDuty = specialistSubDuties.iterator().next().getDuty();
                                        Set<SubDuty> AllSubDuties = temporaryDuty.getSubDuties();
                                        AllSubDuties.removeAll(specialistSubDuties);
                                        if (AllSubDuties.size() == 0) {
                                            throw new CustomNoResultException("this special can not accept any subDuty or accepted full sub duty");
                                        } else {
                                            for (SubDuty subDuty : AllSubDuties
                                            ) {
                                                System.out.println(subDuty);
                                                System.out.println("1)select  2)next specialist");
                                                switch (scanner.nextInt()) {
                                                    case 1 ->
                                                            specialistService.addSpecialistToSubDuty(specialist, subDuty);
                                                    case 2 -> System.out.println();
                                                    default -> System.out.println("number is wrong");
                                                }
                                            }
                                        }
                                    }
                                }
                                case 2 -> System.out.println();
                                default -> System.out.println("inter wrong number");
                            }
                        } catch (CustomNoResultException c) {
                            System.out.println(c.getMessage());
                        }
                    }
                }
                case 8 -> {
                    Set<Specialist> specialistSetCandidateForRemove = new HashSet<>(specialistService.load());
                    for (Specialist specialist : specialistSetCandidateForRemove
                    ) {
                        System.out.println(specialist);
                        System.out.println("--------------------------------------");
                        System.out.println("1)select  2)next specialist");
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                Set<SubDuty> subDuties1 = specialist.getSubDuties();
                                Set<SubDuty> copySubDuties1 = new HashSet<>();
                                for (SubDuty subDuty : subDuties1
                                ) {
                                    System.out.println(subDuty);
                                    System.out.println("------------------------------");
                                    System.out.println("1)select  2)next subDuty");
                                    switch (scanner.nextInt()) {
                                        case 1 -> copySubDuties1.add(subDuty);
                                        case 2 -> System.out.println();
                                        default -> System.out.println("wrong number inter");
                                    }
                                }
                                subDuties1.removeAll(copySubDuties1);
                                specialist.setSubDuties(subDuties1);
                                specialistService.update(specialist);
                            }
                            case 2 -> System.out.println();
                        }
                    }
                }
                case 9 -> specialistService.removeSpecialistFromDuty();
                case 10 -> {
                    try {
                        dutyService.load().forEach(duty -> {
                            System.out.println(duty);
                            System.out.println("1)selected  2)next subDuty");
                            if (scanner.nextInt() == 1) {
                                subDutyService.showAllSubDutyOfDuty(duty).forEach(subDuty -> {
                                    System.out.println(subDuty);
                                    System.out.println("1)selected  2)next subDuty");
                                    if (scanner.nextInt() == 1) {
                                        System.out.println("inter description of subDuty");
                                        String description = scanner.next();
                                        subDutyService.editSubDutyDescription(subDuty, description);
                                    }
                                });
                            }
                        });
                    } catch (CustomException ignored) {
                    }
                }
                case 11 -> {
                    try {
                        dutyService.load().forEach(
                                duty -> {
                                    System.out.println(duty);
                                    System.out.println("1)selected  2)next subDuty");
                                    if (scanner.nextInt() == 1) {
                                        subDutyService.showAllSubDutyOfDuty(duty).forEach(subDuty -> {
                                            System.out.println(subDuty);
                                            System.out.println("1)selected  2)next subDuty");
                                            if (scanner.nextInt() == 1) {
                                                System.out.println("inter basePrice of subDuty");
                                                String basePrice = scanner.next();
                                                subDutyService.editSubDutyPrice(subDuty, basePrice);
                                            }
                                        });
                                    }
                                }
                        );
                    } catch (CustomException ignored) {
                    }
                }
                case 12 -> logIn();
                case 13 -> firstMenu();
            }
        }
    }

    public void specialistSignup() {
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
            System.out.println(duty1);
            System.out.println("1)selected 2)anotherDuty");
            switch (scanner.nextInt()) {
                case 1 -> candidateduty = duty1;
                case 2 -> System.out.println();
            }
        }
        Set<SubDuty> subDuties = new HashSet<>(candidateduty.getSubDuties());
        Set<SubDuty> candidateSetOfSubDuties = new HashSet<>();
        for (SubDuty subDuty : subDuties) {
            System.out.println(subDuty);
            System.out.println("1)selected 2)anotherSubDuty or press any number");
            switch (scanner.nextInt()) {
                case 1 -> candidateSetOfSubDuties.add(subDuty);
                case 2 -> System.out.println();
                default -> System.out.println("inter wrong number");
            }
        }

        Specialist specialist;
        try {
            specialist = Specialist.builder()
                    .duty(candidateduty)
                    .firstName(firstName)
                    .lastname(lastName)
                    .nationalId(nationalId)
                    .email(email)
                    .password(password)
                    .registerDate(LocalDate.now())
                    .registerTime(LocalTime.now())
                    .status(SpecialistRegisterStatus.WAITING_FOR_CONFIRM)
                    .subDuties(candidateSetOfSubDuties)
                    .wallet(walletService.createWallet())
                    .score(0)
                    .imageData(specialistService.convertImageToImageData(imagePath))
                    .build();
            specialistService.addSpecialist(specialist);
        } catch (CustomInputOutputException e) {
            logIn();

        }
        System.out.println("------------------------------");
        System.out.println("please wait until admin confirm you");

        logIn();
    }

    public void setSpecialistSuggestion() {
        CalenderAndValidation calenderAndValidation = new CalenderAndValidation();
        Set<SubDuty> specialistSubDuties = new HashSet<>(CheckValidation.memberTypespecialist.getSubDuties());
        for (SubDuty subDuty : specialistSubDuties
        ) {
            System.out.println(subDuty);
            System.out.println("--------------------------------");
            System.out.println("1)select  2)another subDuty");
            switch (scanner.nextInt()) {
                case 1 -> {
                    Set<Orders> ordersSet = new HashSet<>(orderService.showOrdersToSpecialist(subDuty));
                    if (ordersSet.size() == 0) {
                        System.out.println("no any order exist for this subDuty");
                    } else {
                        for (Orders order : ordersSet) {
                            System.out.println(order);
                            System.out.println("--------------------------------------");
                            System.out.println("1)select  2)another order");
                            switch (scanner.nextInt()) {
                                case 1 -> {
                                    specialistSuggestionService.findSuggestWithThisSpecialistAndOrder(CheckValidation.memberTypespecialist, order);
                                    System.out.println("inter proposed price");
                                    double proposedPrice = scanner.nextDouble();
                                    System.out.println("inter workTime PerHour");
                                    Integer workTimePerHour = scanner.nextInt();
                                    if (calenderAndValidation.setAndConvertTime(order.getTimeOfWork()).equals(order.getTimeOfWork())) {
                                        System.out.println("you entered wrong date");
                                        break;
                                    }
                                    SpecialistSuggestion specialistSuggestion = SpecialistSuggestion.builder()
                                            .specialist(CheckValidation.memberTypespecialist)
                                            .order(orderService.updateOrderToNextLevel(order, OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SELECTION))
                                            .DateOfSuggestion(LocalDate.now())
                                            .TimeOfSuggestion(LocalTime.now())
                                            .TimeOfStartWork(calenderAndValidation.setAndConvertTime(order.getTimeOfWork()))
                                            .durationOfWorkPerHour(workTimePerHour)
                                            .proposedPrice(subDuty.getBasePrice() + proposedPrice)
                                            .build();
                                    specialistSuggestionService.submitSpecialistSuggestion(specialistSuggestion);
                                }

                                case 2 -> System.out.println();
                                default -> System.out.println("inter wrong number ");
                            }
                        }
                    }
                }
                case 2 -> System.out.println();
                default -> System.out.println("inter wrong number ");
            }

        }
    }

    public void showMenuForSpecialist() {
        System.out.println("----------Specialist Menu------------");
        System.out.println("1)login");
        System.out.println("2)change password");
        System.out.println("3)showImage");
        System.out.println("4)setSpecialistSuggestion");
    }

    public void runSpecialistMenu() {
        while (true) {
            showMenuForSpecialist();
            switch (scanner.nextInt()) {
                case 1 -> logIn();
                case 2 -> {
                    System.out.println("email:");
                    String email = scanner.next();
                    System.out.println("old password:");
                    String oldPassword = scanner.next();
                    System.out.println("new password:");
                    String newPassword = scanner.next();
                    specialistService.changePassword(email, oldPassword, newPassword);
                }
                case 3 -> specialistService.convertByteArrayToImage(CheckValidation.memberTypespecialist, "t.jpg");

                case 4 -> setSpecialistSuggestion();

                default -> {
                    try {
                        throw new CustomNoResultException("you enter wrong number or user not found try again or signup");
                    } catch (CustomNoResultException c) {
                        System.out.println(c.getMessage());
                    }
                }
            }
        }
    }

    public void customerSignup() {
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
        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastname(lastName)
                .nationalId(nationalId)
                .email(email)
                .password(password)
                .registerDate(LocalDate.now())
                .registerTime(LocalTime.now())
                .wallet(walletService.createWallet())
                .build();
        customerService.addCustomer(customer);
        logIn();
    }

    public void customerMenu() {
        System.out.println("---------Customer Menu--------");
        System.out.println("1)login");
        System.out.println("2)signup");
        System.out.println("3)submit order");
        System.out.println("4)Show find Orders In Status Waiting For Specialist Suggestion");
        System.out.println("5)Show find Orders In Status Waiting For Specialist Selection");

    }

    public void runCustomerMenu() {
        while (true) {
            customerMenu();
            switch (scanner.nextInt()) {
                case 1 -> logIn();
                case 2 -> customerSignup();
                case 3 -> submitOrders();
                case 4 ->
                        orderService.findOrdersInStatusWaitingForSpecialistSuggestion(CheckValidation.memberTypeCustomer).
                                forEach(System.out::println);
                case 5 ->
                        orderService.findOrdersInStatusWaitingForSpecialistSelection(CheckValidation.memberTypeCustomer).
                                forEach(System.out::println);
            }
        }
    }

    public Address setAddressCustomer() {
        CustomRegex customRegex = new CustomRegex();
        System.out.println("inter province");
        String province = scanner.next();
        System.out.println("inter city");
        String city = scanner.next();
        System.out.println("inter street");
        String street = scanner.next();
        System.out.println("inter postalCode");
        String postalCode = scanner.next();
        System.out.println("inter houseNumber");
        String houseNumber = scanner.next();
        try {
            if (!customRegex.checkOneInputIsValid(houseNumber, customRegex.getValidPrice())) {
                throw new CustomException("house number must have positive number");
            } else {
                return Address.builder()
                        .province(province)
                        .city(city)
                        .street(street)
                        .postalCode(postalCode)
                        .houseNumber(Integer.parseInt(houseNumber))
                        .build();
            }
        } catch (CustomException ce) {
            System.out.println(ce.getMessage());

        }
        return new Address();
    }

    public void submitOrders() {
        Set<Duty> duties = new HashSet<>(dutyService.load());
        for (Duty duty : duties
        ) {
            System.out.println(duty);
            System.out.println("1)select  2)another duty");
            switch (scanner.nextInt()) {
                case 1 -> {
                    Set<SubDuty> subDuties = new HashSet<>(duty.getSubDuties());
                    for (SubDuty subDuty : subDuties) {
                        System.out.println(subDuty);
                        System.out.println("1)select  2)another subDuty");
                        switch (scanner.nextInt()) {
                            case 1 -> {
                                orderService.findOrdersWithThisCustomerAndSubDuty(CheckValidation.memberTypeCustomer, subDuty);
                                Address address = setAddressCustomer();
                                System.out.println("inter proposed price(please notice that this price value add with" +
                                        "sub duty price ");
                                String proposedPrice = scanner.next();
                                System.out.println("write description of order");
                                String description = scanner.next();
                                Orders orders = Orders.builder()
                                        .customer(CheckValidation.memberTypeCustomer)
                                        .subDuty(subDuty)
                                        .description(description)
                                        .address(addressService.createAddress(address))
                                        .DateOfWork(LocalDate.now())
                                        .timeOfWork(LocalTime.now())
                                        .orderStatus(OrderStatus.ORDER_WAITING_FOR_SPECIALIST_SUGGESTION)
                                        .build();
                                orderService.submitOrder(orders, proposedPrice, String.valueOf(subDuty.getBasePrice()));
                                System.out.println("---------------------------------------------------");
                            }
                            case 2 -> System.out.println();
                            default -> System.out.println("inter wrong number ");
                        }
                    }
                }
                case 2 -> System.out.println();
                default -> System.out.println("inter wrong number ");
            }
        }
    }
}

