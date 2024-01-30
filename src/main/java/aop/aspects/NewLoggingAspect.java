package aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewLoggingAspect {
    @Around("execution(public String returnBook())") // С помощью этой аннотации возможно:
    // - произвести какие-либо действия до работы target-метода;
    // - произвести какие-либо действия после работы target-метода;
    // - получить результат работы target-метода/изменить его;
    // - предпринять какие-либо действия, если из target-метода выбрасывается исключение.
    // Особенность этой аннотации в том, что мы берём на себя ответственность по вызову метода.
    public Object aroundReturnBookLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // ProceedingJoinPoint означает связь с target-методом. Мы можем использовать ProceedingJoinPoint, чтобы
        // запустить target-метод.
        // Т.к. метод proceed() класса ProceedingJoinPoint возвращает Object, то и мы должны вернуть Object.
        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку пытаются вернуть книгу");

        Object targetMethodResult = null;
        try {
            proceedingJoinPoint.proceed();
            targetMethodResult = targetMethodResult + " и Преступление и наказание";
        } catch (Exception e) {
            System.out.println("aroundReturnBookLoggingAdvice: было поймано исключение " + e);
            throw e;
        }

        System.out.println("aroundReturnBookLoggingAdvice: в библиотеку успешно вернули книгу");
        return targetMethodResult;
    }
}
