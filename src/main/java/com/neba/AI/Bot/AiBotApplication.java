package com.neba.AI.Bot;

import com.neba.AI.Bot.service.AiService;
import com.neba.AI.Bot.service.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class AiBotApplication {

	public static void main(String[] args) {
		try {
			ApplicationContext context = SpringApplication.run(AiBotApplication.class, args);

			AiService aiService = context.getBean(AiService.class);

			Bot bot = new Bot(aiService);

			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(bot);

		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
