package hibrenate_one_to_many.bi;

import hibrenate_one_to_many.bi.entity.Department;
import hibrenate_one_to_many.bi.entity.Employee;
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

//            Department dep = new Department("Sales", 800, 1500);
//            Employee emp1 = new Employee("Dmitry", "Osipov", 800);
//            Employee emp2 = new Employee("Elena", "Smirnova", 1500);
//            Employee emp3 = new Employee("Anton", "Sidorov", 1200);
//            dep.addEmployeeToDepartment(emp1);
//            dep.addEmployeeToDepartment(emp2);
//            dep.addEmployeeToDepartment(emp3);
//            session.persist(dep);
//
            System.out.println("Get department");
            Department dep2 = session.get(Department.class, 5);
            System.out.println("Show department");
            System.out.println(dep2);
            System.out.println("Show employees of the department");
            System.out.println(dep2.getEmps());
//
//            Employee employee = session.get(Employee.class, 4);
//            session.remove(employee);

            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }

            factory.close();
        }
    }
}
