package aop.aspects;

import aop.Student;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class UniversityLoggingAspect {
    @Before("execution(* getStudents())")
    public void beforeGetStudentsLoggingAdvice() {
        System.out.println("beforeGetStudentsLoggingAdvice: логируем получение списка студентов перед " +
                "методом getStudents");
    }

    @AfterReturning(pointcut = "execution(* getStudents())", returning = "students")  // С помощью AfterReturning можно
    // менять возвращаемый результат метода. Отрабатывает только после успешного завершения метода.
    public void afterReturningGetStudentsLoggingAdvice(List<Student> students) {  // Название returning должно совпадать
        // с именем параметра.
        Student firstStudent = students.getFirst();
        String nameSurname = firstStudent.getNameSurname();
        nameSurname = "Mr. " + nameSurname;
        firstStudent.setNameSurname(nameSurname);

        double avgGrade = firstStudent.getAvgGrade();
        avgGrade = avgGrade + 1;
        firstStudent.setAvgGrade(avgGrade);

        System.out.println("afterReturningGetStudentsLoggingAdvice: логируем получение списка студентов после " +
                "работы метода getStudents");
    }

    @AfterThrowing(pointcut = "execution(* getStudents())", throwing = "exception")  // Обработать исключение здесь
    // невозможно. Отрабатывает только после выбрасывания исключения.
    public void afterThrowingGetStudentsLoggingAdvice(Throwable exception) {
        System.out.println("afterThrowingGetStudentsLoggingAdvice: логируем выброс исключения " + exception);
    }

    @After("execution(* getStudents())") // С помощью этой аннотации невозможно получить доступ к исключению, которое
    // было выброшено из метода с основной логикой. Также невозможно получить доступ к возвращаемому методом результату.
    // Отрабатывает после работы метода (вне зависимости, был ли метод завершён успешно или вылетело исключение).
    public void afterGetStudentsLoggingAdvice() {
        System.out.println("afterGetStudentsLoggingAdvice: логируем нормальное окончание работы метода или выброс " +
                "исключения");
    }
}
