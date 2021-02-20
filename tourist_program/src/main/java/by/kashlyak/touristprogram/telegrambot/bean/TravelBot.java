package by.kashlyak.touristprogram.telegrambot.bean;


import by.kashlyak.touristprogram.telegrambot.service.MessageService;
import by.kashlyak.touristprogram.telegrambot.service.RequestDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.objects.Update;

@PropertySource("application.properties")
@Component
public class TravelBot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

   @Autowired
    RequestDispatcher requestDispatcher;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        requestDispatcher.dispatcher(update);

    }
}
