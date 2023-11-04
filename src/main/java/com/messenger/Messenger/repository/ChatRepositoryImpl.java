package com.messenger.Messenger.repository;

import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.PrivateChat;
import com.messenger.Messenger.domain.PublicChat;
import com.messenger.Messenger.domain.User;
import com.messenger.Messenger.repository.impl.ChatRepositoryInterface;
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
                if (chat.getId() == null) {
                        entityManager.persist(chat);
                } else {
                        entityManager.merge(chat);
                }
                return chat;
        }
}
