package spring_introduction;

public class Cat implements Pet {
    public Cat() {
        System.out.println("Создание кошки через конструктор");
    }

    @Override
    public void say() {
        System.out.println("Meow-meow");
    }
}
