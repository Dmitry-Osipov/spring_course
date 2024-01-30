package aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary {
    public void getBook() {
        System.out.println("Мы берём книгу из UniLibrary\n");
    }

    public String returnBook() {
        int a = 10 / 0;
        System.out.println("Мы возвращаем книгу в UniLibrary\n");
        return "Война и мир";
    }

    public void getMagazine() {
        System.out.println("Мы берём журнал из UniLibrary\n");
    }

    public void returnMagazine() {
        System.out.println("Мы возвращаем журнал в UniLibrary\n");
    }

    public void addBook(String personName, Book book) {
        System.out.println("Мы добавляем книгу в UniLibrary\n");
    }

    public void addMagazine() {
        System.out.println("Мы добавляем журнал в UniLibrary\n");
    }
}
