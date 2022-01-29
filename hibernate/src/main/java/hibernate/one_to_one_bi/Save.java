package hibernate.one_to_one_bi;

import hibernate.one_to_one_bi.entity.Detail;
import hibernate.one_to_one_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Save {
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
            Employee employee = new Employee("Ivan", "Ivanov", "SALES", 500);
            Detail detail = new Detail("Milano", "123454321", "ivan@gmail.com");
            employee.setEmpDetail(detail);
            session.save(employee);

            Employee emp = new Employee("Ivan", "Ivanov", "SALES", 500);
            Detail det = new Detail("Milano", "123454321", "ivan@gmail.com");
            det.setEmployee(emp); // IMPORTANT
            emp.setEmpDetail(det); // IMPORTANT
            session.save(det);
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
