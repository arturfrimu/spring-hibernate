package hibernate.one_to_one_bi;

import hibernate.one_to_one_bi.entity.Detail;
import hibernate.one_to_one_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Delete {
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
            Employee employee = session.get(Employee.class, 40L);
//            session.delete(employee);
            session.createQuery("delete Employee where id=41L").executeUpdate();

            Detail detail = session.get(Detail.class, 44L);
//            session.delete(detail);
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
