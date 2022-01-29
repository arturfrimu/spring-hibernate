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
            Employee employee = session.get(Employee.class, 4L);
            Detail detail = employee.getEmpDetail();
            if (detail == null)
                detail = new Detail("PuertoRico", "963258741", "PuertoRico@gmail.com"); // new details
            employee.setEmpDetail(detail);
            detail.setEmployee(employee);
            session.update(employee);

//            session.createQuery("update Employee set empDetail=2 where id=2").executeUpdate();

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
