package spring_introduction;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test4 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext2.xml");  // Через запятую можно
        // передавать несколько конфиг-файлов.
        Dog myDog = context.getBean("myPet", Dog.class);
        myDog.setName("Белка");
        Dog yourDog = context.getBean("myPet", Dog.class);
        yourDog.setName("Стрелка");

        // Давая кличку объекту при Bean Scope = Singleton, мы получаем изменение состояния и у других объектов. Поэтому
        // Singleton подходит только для объектов, которые не имеют состояния.
        System.out.println(myDog.getName());
        System.out.println(yourDog.getName());

        System.out.println("Переменные ссылаются на один и тот же объект? " + (myDog == yourDog));
        System.out.println(myDog);
        System.out.println(yourDog);

        context.close();
    }
}
