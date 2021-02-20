package by.kashlyak.touristprogram.telegrambot.service;

import by.kashlyak.touristprogram.telegrambot.bean.TravelBot;
import by.kashlyak.touristprogram.telegrambot.buttons.ClientButtons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MessageService {
    @Autowired
    TravelBot travelBot;
    @Autowired
    ClientButtons clientButtons;

    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);

        try {
            travelBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMessageWithKeyboard(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(clientButtons.getMainMenuKeyboard());

        try {
            travelBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
