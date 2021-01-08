package ru.itis.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Chat;
import ru.itis.models.User;
import ru.itis.repositories.ChatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat delete(Long chatID) {
        Chat chat;
        Optional<Chat> chatOptional = chatRepository.findById(chatID);
        if (chatOptional.isPresent()){
            chat = chatOptional.get();
        } else throw new IllegalArgumentException("Chat not found");
        chatRepository.delete(chat);
        List<User> users = chat.getUsers();
        for (User user : users) {
            List<Chat> chats = user.getChats();
            for (int j = 0; j < chats.size(); j++) {
                if (chats.get(j).getId().equals(chat.getId())) {
                    chats.remove(j);
                    break;
                }
            }
        }
        chatRepository.save(chat);
        return chat;
    }
}

