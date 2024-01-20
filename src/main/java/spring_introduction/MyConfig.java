package spring_introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration  // Аннотация Configuration означает, что данный класс является конфигурацией (замена XML).
//@ComponentScan("spring_introduction")  // С помощью аннотации ComponentScan мы показываем, какой пакет нужно сканировать
// на наличие бинов и разных аннотаций.
@PropertySource("classpath:myApp.properties")  // Аннотация указывает, откуда можно взять значение для полей (значения
// запрашиваем с помощью аннотации Value).
public class MyConfig {
    @Bean  // Данная аннотация перехватывает все обращения к бину и регулирует его создание.
    @Scope  // Можно не писать аннотацию, если нужен singleton.
    public Pet catBean() {  // Bean id - название метода.
        return new Cat();
    }

    @Bean
    public Person personBean() {
        return new Person(catBean());
    }
}
