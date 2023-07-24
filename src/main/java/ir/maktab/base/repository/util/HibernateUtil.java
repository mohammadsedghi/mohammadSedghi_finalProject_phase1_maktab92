package ir.maktab.base.repository.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final StandardServiceRegistry ssr=new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml").build();
    private static final Metadata metadata=new MetadataSources(ssr).getMetadataBuilder().build();
    private static final SessionFactory sessionFactory= metadata.getSessionFactoryBuilder().build();
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
