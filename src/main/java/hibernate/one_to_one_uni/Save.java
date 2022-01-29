package hibernate.one_to_one_uni;

import hibernate.one_to_one_uni.entity.Detail;
import hibernate.one_to_one_uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Save {
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
            Employee employee = new Employee("Igor", "Igorovici", "SALES", 600);
            Detail detail = new Detail("Roma", "123456789", "Igor@gmail.com");
            employee.setEmpDetail(detail);
            session.save(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
