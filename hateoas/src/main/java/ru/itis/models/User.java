package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import ru.itis.models.Chat;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private UserRole role;
    @ManyToMany
    private List<Chat> chats;

    public void becomeAdmin(){
        if (this.role.equals(UserRole.USER)){
            this.role = UserRole.ADMIN;
        } else throw new IllegalArgumentException("This user already has admin role");
    }
}