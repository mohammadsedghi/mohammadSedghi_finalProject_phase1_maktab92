package ir.maktab;

import ir.maktab.veiw.Menu;

public class Main {
    public static void main(String[] args){
        Menu menu = new Menu();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        AdminServiceImpl adminService=new AdminServiceImpl(session);
//        AdminRepository adminRepository=new AdminRepositoryImpl(session);
//        Admin admin = Admin.builder()
//                        .firstName("mohammad")
//                      .lastname("sedghi")
//                     .nationalId("4560116814")
//                .registerTime(LocalTime.now())
//                .registerDate(LocalDate.now())
//                .email("mohammadsedghi1993@gmail.com")
//                        .password(adminService.encryptAdminPassword("ad123456"))
//                                .build();
//        session.getTransaction().begin();
//        adminRepository.save(admin);
//        session.getTransaction().commit();

        menu.firstMenu();
    }
}