package by.kashlyak.touristprogram;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class TouristProgramApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TouristProgramApplication.class, args);

    }

}
