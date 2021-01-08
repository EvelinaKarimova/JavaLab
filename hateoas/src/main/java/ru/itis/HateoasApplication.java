package ru.itis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.models.Chat;
import ru.itis.models.ChatState;
import ru.itis.models.User;
import ru.itis.models.UserRole;
import ru.itis.repositories.ChatRepository;
import ru.itis.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        SpringApplication.run(HateoasApplication.class, args);
        ApplicationContext context = SpringApplication.run(HateoasApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);
        User evelina = User.builder()
                .firstName("Evelina")
                .lastName("Karimova")
                .role(UserRole.ADMIN)
                .build();

        User alina = User.builder()
                .firstName("Alina")
                .lastName("Valinurova")
                .role(UserRole.USER)
                .build();


        List<User> chat1Users = new ArrayList<>();
        chat1Users.add(evelina);
        chat1Users.add(alina);

        ChatRepository chatRepository = context.getBean(ChatRepository.class);
        Chat chat1 = Chat.builder()
                .name("Stupid girls from JavaLab")
                .state(ChatState.ACTIVE)
                .users(chat1Users)
                .build();
        chatRepository.save(chat1);

        List<Chat> chatList = new ArrayList<>();
        chatList.add(chat1);
        evelina.setChats(chatList);
        alina.setChats(chatList);
        userRepository.save(evelina);
        userRepository.save(alina);
    }
}
