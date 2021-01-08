package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
