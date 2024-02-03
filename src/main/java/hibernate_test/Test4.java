package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test4 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Employee emp = session.get(Employee.class, 1);
            emp.setSalary(1500);  // Обновление данных происходит по средствам обновления значений в программе
            session.createQuery("update Employee set salary=1000 where name='Alex'").executeUpdate();  // Или с
            // помощью HQL
            session.getTransaction().commit();  // + сохранения новых данных в БД.

            System.out.println("Done!");
        }
    }
}
