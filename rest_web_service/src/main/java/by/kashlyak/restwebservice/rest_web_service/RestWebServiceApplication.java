package by.kashlyak.restwebservice.rest_web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class RestWebServiceApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(RestWebServiceApplication.class, args);
    }

}
