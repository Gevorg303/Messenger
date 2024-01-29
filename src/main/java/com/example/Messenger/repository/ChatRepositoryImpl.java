package com.example.Messenger.repository;

import com.example.Messenger.repository.impl.ChatRepositoryInterface;
import com.example.Messenger.domain.Chat;
import com.example.Messenger.domain.PrivateChat;
import com.example.Messenger.domain.PublicChat;
import com.example.Messenger.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepositoryImpl implements ChatRepositoryInterface {
        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public Chat createNewChat(User creator, String name, boolean isPrivate, String password, int maxUsers) {
                Chat chat;
                if (isPrivate) {
                        chat = new PrivateChat(creator, name, maxUsers, password);
                } else {
                        chat = new PublicChat(creator, name, maxUsers, null);
                }
                return save(chat);
        }

        @Override
        public void delete(Chat chat) {
                entityManager.remove(chat);
        }

        @Override
        public List<Chat> findAll() {
                return entityManager.createQuery("SELECT c FROM Chat c", Chat.class).getResultList();
        }

        @Override
        public Chat findChat(String chatName) {
                return entityManager.createQuery("SELECT c FROM Chat c WHERE c.chatName = :chatName", Chat.class)
                        .setParameter("chatName", chatName)
                        .getResultList()
                        .stream()
                        .findFirst()
                        .orElse(null);
        }
        @Override
        public Chat save(Chat chat) {
                entityManager.persist(chat);
                return chat;
        }
}
