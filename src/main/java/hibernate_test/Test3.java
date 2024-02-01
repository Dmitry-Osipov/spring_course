package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<Employee> emps = session.createQuery("from Employee where name = 'Alex' and salary > 400")
                    .getResultList();  // Пропускаем прописывание "SELECT ...". Получаем сразу список.
            // Hibernate использует HQL, который по синтаксису очень похож на SQL. Важно отметить, что параметры,
            // которые мы передаём, являются не названием столбца, а названием поля соответствующей модели.
            for (Employee emp : emps) {
                System.out.println(emp);
            }
            session.getTransaction().commit();

            System.out.println("Done!");
        }
    }
}
