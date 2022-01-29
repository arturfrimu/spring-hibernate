package hibernate.many_to_many;

import hibernate.many_to_many.entity.Child;
import hibernate.many_to_many.entity.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Save {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();

        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Section section = new Section("Football");
            Child child1 = new Child("Masha", 5);
            Child child2 = new Child("Zaur", 7);
            Child child3 = new Child("Vasia", 6);

            Section section1 = new Section("Math");
            Section section2 = new Section("Chess");
            Section section3 = new Section("Volleyball");
            Child child = new Child("Igor", 10);
            child.addSectionToChild(section1);
            child.addSectionToChild(section2);
            child.addSectionToChild(section3);

            section.addChildToSection(child1);
            section.addChildToSection(child2);
            section.addChildToSection(child3);

            session.beginTransaction();

            session.persist(section); // IMPORTANT Use persist if not use CascadeType.ALL
            session.persist(child);


            session.getTransaction().commit();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
