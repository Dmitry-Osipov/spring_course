package hibernate_one_to_one;


import hibernate_one_to_one.entity.Detail;
import hibernate_one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = null;
        Session session = null;
        try {
            factory = new Configuration()
                    .configure()
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Detail.class)
                    .buildSessionFactory();

            session = factory.getCurrentSession();
            Employee employee = new Employee("Dmitry", "Osipov", "IT", 500);
            Detail detail = new Detail("Tula", "123456789", "dimaosipov00@gmail.com");
            employee.setEmpDetail(detail);
            session.beginTransaction();

            session.persist(employee);  // Благодаря каскаду все данные по детали сохранились в свою таблицу при
            // сохранении сотрудника.

            session.getTransaction().commit();
            System.out.println("Done!");

            session = factory.getCurrentSession();
            Employee employee1 = new Employee("Oleg", "Smirnov", "Sales", 700);
            Detail detail1 = new Detail("Moscow", "987654321", "olejka@gmail.com");
            employee1.setEmpDetail(detail1);
            session.beginTransaction();

            session.persist(employee1);

            session.getTransaction().commit();
            System.out.println("Done!");

            session = factory.getCurrentSession();
            session.beginTransaction();

            Employee emp = session.get(Employee.class, 2);  // Если укажем неверные данные, то получим null.
            // Вместе с тем в логах мы увидим ошибку: обнаружена утечка соединения. Для этого прописываем блок finally.
            System.out.println(emp.getEmpDetail());  // Благодаря каскаду, получаем из базы не только сотрудника, но и
            // детали.
            session.remove(emp);  // Также благодаря каскаду мы из базы удалили и сотрудника, и детали о нём.

            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {  // Важное уточнение: при использовании блока try-with-resources мы всё равно увидим ошибку утечки
            // соединения. Для избежания этого блок finally абсолютно всегда надо прописывать полностью.
            if (session != null) {
                session.close();
            }

            if (factory != null) {
                factory.close();
            }
        }
    }
}
