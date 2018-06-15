package ru.kpfu.olympiad_center.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 15.04.2018
 *
 * @author Robert Bagramov.
 */
@SpringBootApplication
@EntityScan("ru.kpfu.olympiad_center.models")
@ComponentScan("ru.kpfu")
@EnableJpaRepositories(basePackages = "ru.kpfu.olympiad_center.repositories")
public class OlympiadCenterApp {
    public static void main(String[] args) {
        SpringApplication.run(OlympiadCenterApp.class, args);
    }
}
