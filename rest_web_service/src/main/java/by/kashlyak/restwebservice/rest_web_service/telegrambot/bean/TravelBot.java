package by.kashlyak.restwebservice.rest_web_service.telegrambot.bean;


import by.kashlyak.restwebservice.rest_web_service.dao.CitiesDAO;
import by.kashlyak.restwebservice.rest_web_service.telegrambot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


@PropertySource("application.properties")
@Component
public class TravelBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Autowired
    CitiesDAO citiesDAO;
    @Autowired
    MessageService messageService;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (citiesDAO.findByName(text) != null) {
                    String description = citiesDAO.findByName(text).getDescription();
                    messageService.sendMessage(message, description);
                } else {
                    messageService.sendMessage(message, "Я не знаю такого города. Попробуйте снова.");
                }
            }
        }
    }
}
