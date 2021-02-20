package by.kashlyak.touristprogram.restwebservice.controllers;

import by.kashlyak.touristprogram.restwebservice.entity.City;
import by.kashlyak.touristprogram.restwebservice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/patch/{id}")
    public String update(@ModelAttribute("city") City city, @PathVariable("id") Long id) {
        cityRepository.save(city);
        return "redirect:/cities";
    }

    @RequestMapping(value  ="/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        City city = cityRepository.findById(id).get();
        cityRepository.delete(city);

        return "redirect:/cities";
    }
}
