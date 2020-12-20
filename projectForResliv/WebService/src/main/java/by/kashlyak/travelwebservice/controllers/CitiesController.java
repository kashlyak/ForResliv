package by.kashlyak.travelwebservice.controllers;

import by.kashlyak.travelwebservice.dao.CitiesDAO;
import by.kashlyak.travelwebservice.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CitiesController {


    private final CitiesDAO citiesDAO;

    @Autowired
    public CitiesController(CitiesDAO citiesDAO) {
        this.citiesDAO = citiesDAO;
    }



    @GetMapping("/all")
    public String index(Model model) {
        model.addAttribute("cities", citiesDAO.allCitiesFromDB());
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

        citiesDAO.saveNewCity(city);

        return "redirect:/cities/all";
    }


    @GetMapping("/{name}")
    public String show(@PathVariable("name") String name, Model model) {
    model.addAttribute("city", citiesDAO.findByName(name));
    return "cities/show";
    }
    @GetMapping("/{name}/edit")
    public String edit(Model model, @PathVariable("name") String name) {
        model.addAttribute("city", citiesDAO.findByName(name));
        return "cities/edit";
    }
    @PatchMapping("/{name}")
    public String update(@ModelAttribute("city") City city, @PathVariable("name") String name) {
        citiesDAO.update(name,city);
        return "redirect:/cities/all";
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable("name") String name) {
        citiesDAO.delete(name);
        return "redirect:/cities/all";
    }









}
