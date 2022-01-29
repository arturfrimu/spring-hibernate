package hibernate.one_to_many_uni;

import hibernate.one_to_many_uni.entity.Department;
import hibernate.one_to_many_uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Get {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            Department department = session.get(Department.class, 2L);
            System.out.println(department + " " + department.getEmps().size());
            department.getEmps().forEach(System.out::println);

//            Employee employee = session.get(Employee.class, 1L);
//            System.out.println("\n" + employee);

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
