package spring_introduction;

public class Person {
    private Pet pet;
    private String surname;
    private int age;

    public Person() {
        System.out.println("Использовался конструктор без аргументов");
    }

    public Person(Pet pet) {
        System.out.println("Использовался конструктор с аргументом");
        this.pet = pet;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        System.out.println("Использовался сеттер фамилии");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Использовался сеттер возраста");
        this.age = age;
    }

    public void setPet(Pet pet) {
        System.out.println("Использовался сеттер животного");
        this.pet = pet;
    }

    public void callYourPet() {
        System.out.println("Hello, my lovely Pet!");
        pet.say();
    }
}
