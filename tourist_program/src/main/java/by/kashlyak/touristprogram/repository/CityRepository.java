package by.kashlyak.touristprogram.repository;

import by.kashlyak.touristprogram.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Controller;

@Controller
public interface CityRepository extends JpaRepository<City, Long> {
    City findCityByName(String name);
}
