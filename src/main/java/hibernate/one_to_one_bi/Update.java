package hibernate.one_to_one_bi;

import hibernate.one_to_one_bi.entity.Detail;
import hibernate.one_to_one_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Update {
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
            Employee employee = session.get(Employee.class, 23L);
            Detail detail = employee.getEmpDetail();
            if (detail == null)
                detail = new Detail("Milano", "1234554321", "milano@gmail.com"); // new details
            employee.setEmpDetail(detail);
            detail.setEmployee(employee);
            session.update(employee);

            session.createQuery("update Employee set salary=1000 where department='SALES'").executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
