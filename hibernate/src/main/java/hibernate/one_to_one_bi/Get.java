package hibernate.one_to_one_bi;

import hibernate.one_to_one_bi.entity.Detail;
import hibernate.one_to_one_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Get {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 16L);
            List<Employee> employees = session.createQuery("from Employee").getResultList();
            List<Detail> details = session.createQuery("from Department").getResultList();
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
