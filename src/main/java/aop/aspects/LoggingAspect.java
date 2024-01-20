package aop.aspects;

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

    @Before("execution(public void getBook())")  // Это pointcut - выражение, когда должен быть применён Advice.
    public void beforeGetBookAdvice() {  // Advice - это метод, который находится в Aspect-классе. Он определяет, что
        // должно произойти при вызове метода getBook.
        System.out.println("beforeGetBookAdvice: попытка получить книгу");
    }
}
