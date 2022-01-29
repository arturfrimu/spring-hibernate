package hibernate.one_to_many_bi;

import hibernate.one_to_many_bi.entity.Department;
import hibernate.one_to_many_bi.entity.Employee;
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

            System.out.println("\nGet department\n");
            Department department = session.get(Department.class, 2L);
//            System.out.println(department);
            System.out.println("\nShow department employees\n");
           // department.getEmps().forEach(System.out::println);

//            Employee employee = session.get(Employee.class, 1L);
//            System.out.println("\n" + employee);

            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
