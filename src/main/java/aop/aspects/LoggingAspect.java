package aop.aspects;

import aop.Book;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Aspect  // Аннотация говорит о том, что это не простой класс, а Aspect. Aspect - это класс, отвечающий за сквозную.
// функциональность.
@Order(10)  // Order обычно указывают не по прямому порядку (1, 2, 3), а, например, 10, 20, 30, чтобы можно было
// вместить между уже сделанными аспектами ещё один аспект, а не переписывать Order'ы других классов.
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

    @Pointcut("execution(* aop.UniLibrary.get*())")  // При смене pointcut выражения его требуется поменять лишь здесь,
    // ибо всё, что ниже прописано с именем данного метода - лишь ссылки.
    private void allGetMethodsFromUniLibrary() {}  // Объявили pointcut. Плюсы такого подхода: возможность использования
    // созданного pointcut для множества advice; возможность быстрого изменения pointcut выражения для множества advice;
    // возможность комбинирования pointcut.
    @Pointcut("execution(* aop.UniLibrary.return*())")
    private void allReturnMethodsFromUniLibrary() {}
    @Pointcut("allReturnMethodsFromUniLibrary() || allGetMethodsFromUniLibrary()")  // Комбинируем pointcut.
    private void allGetOrReturnMethodsFromUniLibrary() {}
    @Pointcut("execution(* aop.UniLibrary.*(..))")
    private void allMethodsFromUniLibrary() {}
    @Pointcut("execution(public void aop.UniLibrary.returnMagazine())")
    private void returnMagazineFromUniLibrary() {}
    @Pointcut("allMethodsFromUniLibrary() && !returnMagazineFromUniLibrary()")  // Создали pointcut, который включает в
    // себя все методы, кроме returnMagazine.
    private void allMethodsExceptReturnMagazineFromUniLibrary() {}

    @Before("aop.aspects.MyPointcuts.allAddMethods()")  // Это pointcut - выражение, когда должен быть применён Advice.
    // Если мы используем в качестве параметра кастомный класс, то требуется прописать его название полностью
    // (пакет + название класса).
    public void beforeAddLoggingAdvice(JoinPoint joinPoint) {  // Advice - это метод, который находится в Aspect-классе.
        // Он определяет, что должно произойти при вызове метода getBook.
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("methodSignature = " + methodSignature);
        System.out.println("methodSignature.getMethod() = " + methodSignature.getMethod());
        System.out.println("methodSignature.getReturnType() = " + methodSignature.getReturnType());
        System.out.println("methodSignature.getName() = " + methodSignature.getName());

        if (methodSignature.getName().equals("addBook")) {
            Object[] arguments = joinPoint.getArgs();
            for (Object obj : arguments) {
                if (obj instanceof Book) {
                    Book myBook = (Book) obj;
                    System.out.println("Информация о книге: название - " + myBook.getName() + ", автор - " +
                            myBook.getAuthor() + ", год издания - " + myBook.getYearOfPublication());
                } else if (obj instanceof String) {
                    System.out.println("Книгу в библиотеку добавляет " + obj);
                }
            }
        }

        System.out.println("beforeGetLoggingAdvice: writing Log #1");
    }

    @Before("allReturnMethodsFromUniLibrary()")
    public void beforeReturnLoggingAdvice() {
        System.out.println("beforeReturnLoggingAdvice: writing Log #2");
    }

    @Before("allGetOrReturnMethodsFromUniLibrary()")
    public void beforeGetOrReturnLoggingAdvice() {
        System.out.println("beforeGetOrReturnLoggingAdvice: writing Log #3");
    }

    @Before("allMethodsExceptReturnMagazineFromUniLibrary()")
    public void beforeAllMethodsExceptReturnMagazineAdvice() {
        System.out.println("beforeAllMethodsExceptReturnMagazine: writing Log#4");
    }
}
