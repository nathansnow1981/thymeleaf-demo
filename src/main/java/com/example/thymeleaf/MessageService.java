package com.example.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    ContactMessageRepository messageRepository;

    public ContactMessage addMessage(ContactMessage contactMessage) {
        return messageRepository.save(contactMessage);
    }

    public List<ContactMessage> getAllMessages(){
        return messageRepository.findAll();
    }
}
