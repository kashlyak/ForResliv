package by.kashlyak.touristprogram.rest_web_service.controllers;

import by.kashlyak.touristprogram.rest_web_service.entity.City;
import by.kashlyak.touristprogram.rest_web_service.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class CitiesController {
    @Autowired
    CityRepository cityRepository;


    @GetMapping("/cities")
    public String index(Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        return "cities/all";
    }

    @GetMapping("/new")
    public String newCity(@ModelAttribute("city") City city) {
        return "cities/new";
    }

    @PostMapping
    public String createNewCity(@RequestParam("name") String name, @RequestParam("description") String description, City city) {
        city.setName(name);
        city.setDescription(description);

        cityRepository.save(city);

        return "redirect:/cities/all";
    }


    @GetMapping("/{name}")
    public String show(@PathVariable("name") String name, Model model) {
        model.addAttribute("city", cityRepository.findCityByName(name));
        return "cities/show";
    }

    @GetMapping("/{name}/edit")
    public String edit(Model model, @PathVariable("name") String name) {
        model.addAttribute("city", cityRepository.findCityByName(name));
        return "cities/edit";
    }

//    @RequestMapping("/{name}")
//    public String update(@ModelAttribute("city") City city, @PathVariable("name") String name) {
//        citiesDAO.update(name, city);
//        return "redirect:/cities/all";
//    }

    @RequestMapping("/delete{name}")
    public String delete(@PathVariable("name") String name) {
        cityRepository.deleteCityByName(name);
        return "redirect:/cities/all";
    }
}
