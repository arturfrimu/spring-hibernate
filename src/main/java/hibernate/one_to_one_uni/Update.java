package hibernate.one_to_one_uni;

import hibernate.one_to_one_uni.entity.Detail;
import hibernate.one_to_one_uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Update {
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
            Employee employee = session.get(Employee.class, 18L);
            Detail detail = employee.getEmpDetail();
            if (detail == null) {
                detail = new Detail("Milano", "1234554321", "milano@gmail.com");
            }
            employee.setEmpDetail(detail);
            session.update(employee);
            session.createQuery("update Employee set salary=1000 where id=18L").executeUpdate();
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
