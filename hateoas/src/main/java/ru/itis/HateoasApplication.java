package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateoasApplication.class, args);
    }
    ApplicationContext context = SpringApplication.run(HateoasApplication.class,args);

    UserRepository coursesRepository = context.getBean(UserRepository.class);
    User evelina = User.builder()
            .id(1L)
            .firstName("Evelina")
            .lastName("Karimova")
            .build();
}
