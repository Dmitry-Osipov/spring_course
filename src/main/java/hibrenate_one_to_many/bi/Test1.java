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
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

//            Department dep = new Department("IT", 300, 1200);
//            Employee emp1 = new Employee("Dmitry", "Osipov", 800);
//            Employee emp2 = new Employee("Elena", "Smirnova", 1000);
//            dep.addEmployeeToDepartment(emp1);
//            dep.addEmployeeToDepartment(emp2);
//            session.persist(dep);
//
//            Department dep2 = session.get(Department.class, 1);
//            System.out.println(dep2);
//            System.out.println(dep2.getEmps());

            Employee employee = session.get(Employee.class, 4);
            session.remove(employee);

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
