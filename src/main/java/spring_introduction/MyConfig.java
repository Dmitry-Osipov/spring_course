package spring_introduction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // Аннотация Configuration означает, что данный класс является конфигурацией (замена XML).
@ComponentScan("spring_introduction")  // С помощью аннотации ComponentScan мы показываем, какой пакет нужно сканировать
// на наличие бинов и разных аннотаций.
public class MyConfig {
}
