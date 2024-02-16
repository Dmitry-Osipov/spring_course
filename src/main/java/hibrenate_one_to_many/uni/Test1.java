package hibrenate_one_to_many.uni;

import hibrenate_one_to_many.uni.entity.Department;
import hibrenate_one_to_many.uni.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            Department dep = new Department("HR", 500, 1500);
            Employee emp1 = new Employee("Oleg", "Ivanov", 800);
            Employee emp2 = new Employee("Andrey", "Sidorov", 100);
            dep.addEmployeeToDepartment(emp1);
            dep.addEmployeeToDepartment(emp2);
            session.persist(dep);  // Удалось сохранить сотрудников с данными по департаменту, хотя сотрудник ничего не
            // знает про департамент.

            session.remove(session.get(Department.class, 3));

            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }

            factory.close();
        }
    }
}
