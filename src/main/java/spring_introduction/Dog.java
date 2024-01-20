package spring_introduction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component  // Если не производится сканирование пакета, то и от аннотации Component смысла нет.
@Scope("prototype")  // Для указания скопа по умолчанию можно ничего не писать.
public class Dog implements Pet {
    public Dog() {
        System.out.println("Создание собаки через конструктор");
    }

    @Override
    public void say() {
        System.out.println("Bow-Wow");
    }

    @PostConstruct
    public void init() {  // Метод инициализации срабатывает сразу после создания класса. Модификатор доступа, как и
        // тип возвращаемых значений, может быть любым. Параметров быть не должно.
        System.out.println("Init method класса собаки");
    }

    @PreDestroy
    public void destroy() {  // Метод уничтожения срабатывает перед завершением приложения (закрытием контекста). Однако
        // если Bean Scope = prototype, то метод инициализации будет вызываться для каждого объекта, а метод уничтожения
        // вызываться не будет. Программисту необходимо самостоятельно писать код для закрытия/освобождения ресурсов.
        System.out.println("Destroy method класса собаки");
    }
}
