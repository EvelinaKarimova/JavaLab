package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chat {
    @ManyToMany
    private List<User> users;
    private String name;
    private ChatState state;

    @Id
    @GeneratedValue
    private Long id;

    public void delete() {
        if (this.state.equals(ChatState.ACTIVE)) {
            this.state = ChatState.DELETED;
        } else throw new IllegalArgumentException("This chat is already deleted");
    }
}
