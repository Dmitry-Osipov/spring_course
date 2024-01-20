package spring_introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test5 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);  // Ранее мы создавали контекст, используя XML,
        // но т.к. теперь мы используем аннотации, нам понадобился новый класс.

        Pet cat = context.getBean("catBean", Pet.class);
        Pet newCat = context.getBean("catBean", Pet.class);
        cat.say();
        System.out.println(cat == newCat);

        Person person = context.getBean("personBean", Person.class);
        Person newPerson = context.getBean("personBean", Person.class);
        person.callYourPet();
        System.out.println(person == newPerson);
        System.out.println(person.getSurname() + " " + person.getAge());

        context.close();
    }
}
