package com.example.messenger.repository;

import com.example.messenger.repository.api.ChatRepositoryInterface;
import com.example.messenger.domain.Chat;
import com.example.messenger.domain.PrivateChat;
import com.example.messenger.domain.PublicChat;
import com.example.messenger.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                /*Получаем CriteriaBuilder, который позволяет создавать критерии запросов*/
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();/**/
                /*Создаем CriteriaQuery для запроса объектов определенного типа (в данном случае, Chat)*/
                CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(Chat.class);
                /*Создаем корневой элемент (Root) для указанной сущности Chat*/
                Root<Chat> root = criteriaQuery.from(Chat.class);
                /*Указываем, что результатом запроса будет объект типа Chat*/
                criteriaQuery.select(root);
                /*Возвращаем список найденных чатов*/
                return entityManager.createQuery(criteriaQuery).getResultList();
        }

        @Override
        public Chat findChat(String chatName) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Chat> criteriaQuery = criteriaBuilder.createQuery(Chat.class);
                Root<Chat> root = criteriaQuery.from(Chat.class);
                /*Добавляем условие, что значение поля chatName должно быть равно заданному имени чата*/
                criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("chatName"), chatName));
                /*Выполняем запрос, получаем результат в виде списка, выбираем первый элемент*/
                return entityManager.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
        }

        @Override
        public Page<Chat> findAll(Pageable pageable) {
                TypedQuery<Chat> query = entityManager.createQuery("SELECT c FROM Chat c", Chat.class);
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());

                List<Chat> chats = query.getResultList();
                long total = entityManager.createQuery("SELECT COUNT(c) FROM Chat c", Long.class).getSingleResult();

                return new PageImpl<>(chats, pageable, total);
        }

        @Override
        public Chat save(Chat chat) {
                entityManager.persist(chat);
                return chat;
        }
}
