package spring_introduction;

import org.springframework.stereotype.Component;

//@Component("catBean")  // Если к аннотации @Component не прописать bean id, то бину будет назначен дефолтный id.
// Дефолтный id получается из имени класса, заменяя его первую заглавную букву на прописную. Однако если хотя бы 2
// заглавные буквы в начале идут подряд, то id будет равен названию класса точь-в-точь.
// Если не производится сканирование пакета, то и от аннотации Component смысла нет.
public class Cat implements Pet {
    public Cat() {
        System.out.println("Создание кошки через конструктор");
    }

    @Override
    public void say() {
        System.out.println("Meow-meow");
    }
}
