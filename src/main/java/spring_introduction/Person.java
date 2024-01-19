package spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("personBean")
public class Person {
//    @Autowired  // Под капотом Spring внедрит зависимость, используя рефлексию. Данный вариант не является
//    // рекомендуемым.
//    @Qualifier("catBean")  // Если при использовании аннотации Autowired подходящих по типу бинов больше одного, то
//    // выбрасывается исключение. Предотвратить выброс данного исключения можно, конкретно указав, какой бин должен
//    // быть внедрён. Для этого и существует аннотация Qualifier.
    private Pet pet;

    @Value("${person.surname}")  // Для внедрения строк и других значений можно использовать аннотацию Value. В этом
    // случае в сеттерах нет необходимости, как это было при конфигурации с помощью XML.
    private String surname;

    @Value("${person.age}")  // Всегда требуется передавать строку.
    private int age;

    public Person() {
        System.out.println("Использовался конструктор без аргументов");
    }

    @Autowired  // Автоматическое внедрение зависимостей благодаря аннотации Autowired. Начиная с версии Spring 7, при
    // наличии у класса одного конструктора, можно не указывать аннотацию Autowired, ибо Spring будет внедрять
    // зависимость на одном этом конструкторе.
    public Person(@Qualifier("catBean") Pet pet) {  // Аннотацию Qualifier для конструктора необходимо писать перед
        // параметром конструктора (в документации указано, что тип элемента не может быть конструктором для данной
        // аннотации).
        System.out.println("Использовался конструктор с аргументом");
        this.pet = pet;
    }

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

//    @Autowired  // Необязательно эту аннотацию подставлять именно к сеттеру. По факту её можно использовать с любым
//    // методом.
//    @Qualifier("dog")
    public void setPet(Pet pet) {
        System.out.println("Использовался сеттер животного");
        this.pet = pet;
    }

    public void callYourPet() {
        System.out.println("Hello, my lovely Pet!");
        pet.say();
    }
}
