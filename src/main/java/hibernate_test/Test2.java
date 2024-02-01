package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("Oleg", "Sidorov", "HR", 700);
            session.beginTransaction();
            session.persist(emp);

            int myId = emp.getId();
            Employee employee = session.get(Employee.class, myId);  // Для получения конкретного экземпляра класса нужно
            // прописать сам класс + id, по которому пройдёт выборка. P.s.: если работать в одной сессии, то повторного
            // запроса к БД не будет.
            session.getTransaction().commit();

            System.out.println("Done!");
            System.out.println(employee);
        }
    }
}
