package by.kashlyak.touristprogram.telegrambot.service;

import by.kashlyak.touristprogram.rest_web_service.entity.City;
import by.kashlyak.touristprogram.rest_web_service.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class RequestDispatcher {
    @Autowired
    MessageService messageService;
    @Autowired
    CityRepository cityRepository;

    public void dispatcher(Update update) {
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
            if (text.toLowerCase().equals("/start")) {
                messageService.sendMessageWithKeyboard(update.getMessage(),
                        "Привет! Я бот, который расскажет тебе о местах в городах, которые стоит посетить! Напиши мне город, пожалуйста!");
            }else if (cityRepository.findCityByName(text) != null) {
                String description = cityRepository.findCityByName(text).getDescription();
                if (description != null) {
                    messageService.sendMessage(update.getMessage(), description);
                }
            } else if(text.toLowerCase().equals("города, которые я знаю")) {
                List<City> all = cityRepository.findAll();
                String message = "";
                for (City city : all) {
                    message = message + city.getName() + "\n";
                }
                messageService.sendMessage(update.getMessage(), message);
            }else {
                    messageService.sendMessage(update.getMessage(), "Прости, я пока ничего не знаю про этот город. Напиши @kashlyak и он все поправит!");
                }


            }

        }
    }


