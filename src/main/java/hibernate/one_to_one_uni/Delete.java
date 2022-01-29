package hibernate.one_to_one_uni;

import hibernate.one_to_one_uni.entity.Detail;
import hibernate.one_to_one_uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Delete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, 14L);
            session.delete(emp);
            session.createQuery("delete Employee where id=15L").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
