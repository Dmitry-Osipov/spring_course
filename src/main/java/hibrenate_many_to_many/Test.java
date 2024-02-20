package hibrenate_many_to_many;

import hibrenate_many_to_many.entity.Child;
import hibrenate_many_to_many.entity.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            Section section1 = new Section("Волейбол");
            Section section2 = new Section("Баскетбол");
            Section section3 = new Section("Танцы");
            Child child1 = new Child("Venik", 15);
            Child child2 = new Child("Dasha", 13);
            Child child3 = new Child("Denis", 16);
            Child child4 = new Child("Igor", 10);
            child4.addSectionToChild(section1);
            child4.addSectionToChild(section2);
            child4.addSectionToChild(section3);

            section1.addChildToSection(child1);
            section1.addChildToSection(child2);
            section1.addChildToSection(child3);

            session.beginTransaction();

            session.persist(section1);

            session.persist(child4);

            Section section = session.get(Section.class, 5);
            System.out.println(section.getChildren());

            Child child = session.get(Child.class, 4);
            System.out.println(child.getSections());

            session.remove(section);

            Child newChild = session.get(Child.class, 4);
            session.remove(newChild);

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            if (session != null) {
                session.close();
            }

            factory.close();
        }
    }
}
