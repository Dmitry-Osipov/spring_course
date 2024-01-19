package spring_introduction;

public class Dog implements Pet {
    public Dog() {
        System.out.println("Создание собаки через конструктор");
    }

    @Override
    public void say() {
        System.out.println("Bow-Wow");
    }

    public void init() {  // Метод инициализации срабатывает сразу после создания класса. Модификатор доступа, как и
        // тип возвращаемых значений, может быть любым. Параметров быть не должно.
        System.out.println("Init method класса собаки");
    }

    public void destroy() {  // Метод уничтожения срабатывает перед завершением приложения (закрытием контекста). Однако
        // если Bean Scope = prototype, то метод инициализации будет вызываться для каждого объекта, а метод уничтожения
        // вызываться не будет. Программисту необходимо самостоятельно писать код для закрытия/освобождения ресурсов.
        System.out.println("Destroy method класса собаки");
    }
}
