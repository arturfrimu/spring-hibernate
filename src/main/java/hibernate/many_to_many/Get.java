package hibernate.many_to_many;

import hibernate.many_to_many.entity.Child;
import hibernate.many_to_many.entity.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Get {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Section section = session.get(Section.class, 6L);
            System.out.println(section);
            System.out.println(section.getChildren());

            Child child = session.get(Child.class, 7L);
            System.out.println(child);
            System.out.println(child.getSections());
            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
