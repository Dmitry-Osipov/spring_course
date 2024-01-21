package aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary {
    public void getBook() {
        System.out.println("Мы берём книгу из UniLibrary\n");
    }

    public void returnBook() {
        System.out.println("Мы возвращаем книгу в UniLibrary\n");
    }

    public void getMagazine() {
        System.out.println("Мы берём журнал из UniLibrary\n");
    }
}
