package com.messenger.Messenger;

import com.messenger.Messenger.domain.Admin;
import com.messenger.Messenger.domain.Chat;
import com.messenger.Messenger.domain.TextMessage;
import com.messenger.Messenger.domain.User;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MessengerApplication {

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MessengerApplication.class);
//		Messenger messenger = context.getBean(Messenger.class);
//		Admin admin = messenger.doNewAdmin("Roma");
//		User user1 = messenger.doNewUser("Vlad");
//		User user2 = messenger.doNewUser("Dima");
//		User user3 = messenger.doNewUser("Lera");
//
//		Chat chat1 = messenger.doNewChat(user1, "Подслушено в Костроме", false,
//				null, 5);
//		Chat chat2 = messenger.doNewChat(user2, "1 класс", true,
//				"12345", 3);
//
//		messenger.addUserToChat(user3, chat1, null);//user3 добавляется в публичный чат
//		messenger.addUserToChat(admin, chat2, "12345");//админ добавляется в приватный чат
//		messenger.writeMessage(user1, chat1, new TextMessage("Я "+user1.getNameUser()+ " всем привет!"));//user1 отправляет сообщение в чат (может отправлять сообщения только в чаты в которых он присутсвует)
//		//messenger.removeChat(user1, chat1); //пользователь удаляет свой чат (чат другого пользователя удалить не может)
//		//messenger.removeChatAdmin(admin, chat1); //админ удаляет чат (может удалить любой)
//		messenger.changeChatMaxUsers(admin, chat1, 10); //админ изменяет максимальное количество пользователей в чате
//		messenger.removeUser(user2); //удаляем пользователя user2 и вместе с ним удаляются все его чаты
//		System.out.println(messenger.printListChat(user1)); //вывести список чатов пользователя user1
//		System.out.println(messenger.printListChat(user2)); //вывести список чатов пользователя user2
//		messenger.findUser("Vlad");
		new SpringApplicationBuilder(MessengerApplication.class)
				.bannerMode(Banner.Mode.OFF) // <- Отключаем Spring баннер
				.run(args);

		System.out.println("App running ...");
	}
}
