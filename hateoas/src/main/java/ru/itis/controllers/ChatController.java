package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.itis.services.ChatServiceImpl;

@RepositoryRestController
public class ChatController {

    @Autowired
    private ChatServiceImpl chatService;

    @RequestMapping(value = "/chats/{chat-id}/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("chat-id") Long chatId) {
        return ResponseEntity.ok(EntityModel.of(chatService.delete(chatId)));
    }
}
