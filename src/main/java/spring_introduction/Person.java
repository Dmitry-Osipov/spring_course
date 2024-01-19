package spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("personBean")
public class Person {
    private Pet pet;
    private String surname;
    private int age;

    public Person() {
        System.out.println("Использовался конструктор без аргументов");
    }

//    @Autowired  // Автоматическое внедрение зависимостей благодаря аннотации Autowired. Начиная с версии Spring 7, при
//    // наличии у класса одного конструктора, можно не указывать аннотацию Autowired, ибо Spring будет внедрять
//    // зависимость на одном этом конструкторе.
//    public Person(Pet pet) {
//        System.out.println("Использовался конструктор с аргументом");
//        this.pet = pet;
//    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        System.out.println("Использовался сеттер фамилии");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Использовался сеттер возраста");
        this.age = age;
    }

    @Autowired  // Необязательно эту аннотацию подставлять именно к сеттеру. По факту её можно использовать с любым
    // методом.
    public void setPet(Pet pet) {
        System.out.println("Использовался сеттер животного");
        this.pet = pet;
    }

    public void callYourPet() {
        System.out.println("Hello, my lovely Pet!");
        pet.say();
    }
}
