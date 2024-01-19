package spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        // Основные функции, выполняемые спринг контейнером: IoC - инверсия управления
        // (создание и управления объектами), DI - внедрение зависимостей.
        // Создаём спринг контейнер:
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        Pet pet = context.getBean("myPet", Pet.class);
        pet.say();
        context.close();
    }
}
