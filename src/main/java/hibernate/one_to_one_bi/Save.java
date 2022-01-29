package hibernate.one_to_one_bi;

import hibernate.one_to_one_bi.entity.Detail;
import hibernate.one_to_one_bi.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Save {
    public static void main(String[] args) {
        new Save().saveEmployee(new Employee("Artur", "Frimu", "IT", 2000), new Detail("Chisinau", "1234554321", "artur@gmail.com"));
        new Save().saveDetail(new Detail("Chisinau", "1234554321", "artur@gmail.com"), new Employee("Artur", "Frimu", "IT", 2000));
    }

    Long saveEmployee(Employee newEmployee, Detail detail){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            newEmployee.setEmpDetail(detail);
            session.save(newEmployee);
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
        return newEmployee.getId();
    }

    void saveDetail(Detail detail, Employee employee){
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            employee.setEmpDetail(detail); // IMPORTANT
            detail.setEmployee(employee); // IMPORTANT
            session.save(detail);
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
