package by.kashlyak.touristprogram.rest_web_service.repository;


import by.kashlyak.touristprogram.rest_web_service.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CityRepository extends JpaRepository<City, Long> {
    City findCityByName(String name);
    City deleteCityByName(String name);

}
