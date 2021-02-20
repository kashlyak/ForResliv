package by.kashlyak.touristprogram.restwebservice.repository;


import by.kashlyak.touristprogram.restwebservice.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;


@RepositoryRestController
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findCityByName(String name);



}
