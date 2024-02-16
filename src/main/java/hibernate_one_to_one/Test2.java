package hibernate_one_to_one;

import hibernate_one_to_one.entity.Employee;
import hibernate_one_to_one.entity.Detail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Employee employee = new Employee("Misha", "Sidorov", "HR", 850);
            Detail detail = new Detail("London", "567816281", "mishanya@gmail.com");
            employee.setEmpDetail(detail);  // Требуется обязательно указывать детали и сотрудника для Bi-directional
            // связи. Если только деталям установить сотрудника, то у сотрудника в поле details_id будет стоять null.
            detail.setEmployee(employee);
            session.persist(detail);

            Detail detail1 = session.get(Detail.class, 3);
            session.remove(detail1);  // Благодаря каскаду в Bi-directional отношении, удалился и сотрудник,
            // для которого мы удалили детали.
            // Если нам потребуется удалить деталь со связью Uni-directional (т.е. только Employee знает про Detail), то
            // сначала мы должны почистить все данные со ссылкой на тот объект, который мы должны удалить, а затем
            // удалить и сам объект.

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
