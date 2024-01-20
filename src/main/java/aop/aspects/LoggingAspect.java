package aop.aspects;

import aop.AbstractLibrary;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect  // Аннотация говорит о том, что это не простой класс, а Aspect. Aspect - это класс, отвечающий за сквозную.
// функциональность.
public class LoggingAspect {
    // Advice типы:
    // * Before - выполняется до метода с основной логикой;
    // * After returning - выполняется только после нормального окончания метода с основной логикой;
    // * After throwing - выполняется после метода с основной логикой, если было выброшено исключение;
    // * After/After finally - выполняется после окончания метода с основной логикой;
    // * Around - выполняется до и после метода с основной логикой.

    // Wildcard типы у pointcut:
    // * любой модификатор доступа - просто не указать модификатор доступа (необязательный параметр);
    // * возвращаемое значение - * (обязательный параметр) - пример: public * doAction();
    // * название - * (обязательный параметр) - можно либо полностью не указать название, либо указать только начало -
    // пример: public void get*(String);
    // * параметры и тип параметров - .. (обязательный параметр) - пример: public Integer calculateSum(..);
    // * исключение - просто не указать исключение (необязательный параметр).

    @Before("execution(public void getBook(aop.Book))")  // Это pointcut - выражение, когда должен быть применён Advice.
    // Если мы используем в качестве параметра кастомный класс, то требуется прописать его название полностью
    // (пакет + название класса).
    public void beforeGetBookAdvice() {  // Advice - это метод, который находится в Aspect-классе. Он определяет, что
        // должно произойти при вызове метода getBook.
        System.out.println("beforeGetBookAdvice: попытка получить книгу");
    }

    @Before("execution(public * returnBook())")  // Данное выражение сработает на любой тип возвращаемых данных.
    public void beforeReturnBook() {
        System.out.println("beforeReturnBook: попытка вернуть книгу");
    }
}
