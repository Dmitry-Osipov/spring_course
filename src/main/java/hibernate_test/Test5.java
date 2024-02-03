package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test5 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, 1);
            session.remove(emp);  // Удалили сотрудника с помощью объекта.
            session.createQuery("delete Employee where name='Alex'").executeUpdate();  // Удалили с помощью HQL.
            session.getTransaction().commit();  // Сохранили изменения.

            System.out.println("Done!");
        }
    }
}
