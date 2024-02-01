package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class Test1 {
    public static void main(String[] args) {
        // SessionFactory - фабрика по производству сессий.
        // SessionFactory читает файл конфига hibernate. После чего SessionFactory знает, как должны создаваться сессии.
        // В Java приложении достаточно создать объект SessionFactory 1 раз и затем можно его переиспользовать.
        // Если мы используем стандартное название файла, как сейчас,
        // то это самое название можно вообще не указывать.
        try (SessionFactory factory = new Configuration()
                .configure()  // Если мы используем стандартное название файла, как сейчас,
                // то это самое название можно вообще не указывать.
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {

            // Session - это обёртка вокруг подключения к БД с помощью JDBC.
            Session session = factory.getCurrentSession();

            Employee emp = new Employee("Alex", "Ivanov", "IT", 600);
            session.beginTransaction();  // Всегда при любых действиях сначала открываем транзакцию.
            session.persist(emp);  // Выполняем какие-либо действия.
            session.getTransaction().commit();  // Всегда при любых действиях в конце закрываем транзакцию.

            System.out.println("Done!");
            System.out.println(emp);  // Вид работника и значения из БД будут полностью идентичны.
        }
    }
}
