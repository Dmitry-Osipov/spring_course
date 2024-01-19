package spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test4 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext2.xml");  // Через запятую можно
        // передавать несколько конфиг-файлов.
        Dog myDog = context.getBean("myPet", Dog.class);
        myDog.say();
        myDog.destroy();

        Dog yourDog = context.getBean("myPet", Dog.class);
        yourDog.say();
        yourDog.destroy();

        context.close();
    }
}
