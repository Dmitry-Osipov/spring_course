package hibernate_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
Entity класс - это Java класс, который отображает информацию определённой таблицы в БД.
Entity класс - это POJO класс, в котором мы используем определённые Hibernate аннотации для связи класса с таблицей из
БД.
POJO (Plain Old Java Object) - класс, удовлетворяющий ряду условий: private поля, getter-ы и setter-ы, конструктор без
аргументов и т.д.
 */
@Entity  // Эта аннотация говорит о том, что данный класс будет иметь отображение в БД.
@Table(name = "employees")  // Эта аннотация говорит о том, к какой именно таблице мы привязываем класс.
public class Employee {
    @Id  // Эта аннотация говорит о то, что в таблице столбец, связанный с данным полем, является Primary Key.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Эта аннотация описывает стратегию по генерации значений для
    // столбца с Primary Key. Например, данная стратегия полагается на автоматическое увеличение столбца по правилам,
    // прописанным в БД. Является оптимальной и самой популярной стратегией.
    // Другие варианты стратегий:
    // AUTO - дефолтный тип. Выбор стратегии будет зависеть от типа БД, с которой мы работаем.
    // SEQUENCE полагается на работу Sequence, созданного в БД. Не поддерживается в MySQL, хорошая производительность.
    // TABLE полагается на значение столбца таблицы БД. Цель такой таблицы - поддержка уникальности значений. Способ
    // устаревший и низкопроизводительный.
    @Column(name = "id")  // Эта аннотация говорит о том, к какому именно столбцу из таблицы мы привязываем поле класса.
    private int id;
    @Column(name = "name")  // Для аннотаций Table и Column можно не указывать атрибут name, если мы понимаем, что
    // таблица или столбцы таблицы называются точно так же.
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "salary")
    private int salary;

    public Employee() {
    }

    public Employee(String name, String surname, String department, int salary) {
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
