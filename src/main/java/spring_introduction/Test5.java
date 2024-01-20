package spring_introduction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test5 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);  // Ранее мы создавали контекст, используя XML,
        // но т.к. теперь мы используем аннотации, нам понадобился новый класс.

        Person person = context.getBean("personBean", Person.class);
        person.callYourPet();

        context.close();
    }
}
