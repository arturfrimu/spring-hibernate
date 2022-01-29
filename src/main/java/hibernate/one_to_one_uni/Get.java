package hibernate.one_to_one_uni;

import hibernate.one_to_one_uni.entity.Detail;
import hibernate.one_to_one_uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Get {
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
            Employee employee = session.get(Employee.class, 13L);
            List<Employee> emps1 = session.createQuery("from Employee").getResultList();
            List<Employee> emps2 = session.createQuery("from Employee where name = 'Zaur' AND salary > 500").getResultList();
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
