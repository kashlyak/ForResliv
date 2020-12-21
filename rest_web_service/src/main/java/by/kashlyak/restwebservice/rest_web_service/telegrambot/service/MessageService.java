package by.kashlyak.restwebservice.rest_web_service.telegrambot.service;




import by.kashlyak.restwebservice.rest_web_service.telegrambot.bean.TravelBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MessageService {

    @Autowired
    TravelBot travelBot;




    public void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);


        try {
            travelBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }

    }}