package by.kashlyak.touristprogram.rest_web_service.controllers;

import by.kashlyak.touristprogram.rest_web_service.entity.City;
import by.kashlyak.touristprogram.rest_web_service.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/cities")
public class CitiesController {
    @Autowired
    CityRepository cityRepository;


    @GetMapping
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

        return "redirect:/cities";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        model.addAttribute("city", cityRepository.findById(id).get());
        return "cities/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("city", cityRepository.findById(id).get());
        return "cities/edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("city") City city, @PathVariable("id") Long id) {
        cityRepository.save(city);
        return "redirect:/cities";
    }

    @DeleteMapping(value  ="/{id}")
    public String delete(@PathVariable("id") Long id) {
        City city = cityRepository.findById(id).get();
        cityRepository.delete(city);
        cityRepository.deleteById(id);
        cityRepository.deleteAll();
        return "redirect:/cities";
    }
}
