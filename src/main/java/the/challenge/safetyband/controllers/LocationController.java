package the.challenge.safetyband.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import the.challenge.safetyband.domain.Location;
import the.challenge.safetyband.repositories.LocationRepository;

@Controller
public class LocationController {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

//    @PostMapping(path = "/add")
//    public @ResponseBody String addNewLocation (@RequestParam String breedtegraad, String lengtegraad, String land, String provincie, String stad, String postcode, String huisnummer, String straatnaam) {
//        Location n = new Location();
//        n.setBreedtegraad(breedtegraad);
//        n.setLengtegraad(lengtegraad);
//        n.setLand(land);
//        n.setProvincie(provincie);
//        n.setStad(stad);
//        n.setPostcode(postcode);
//        n.setHuisnummer(huisnummer);
//        n.setStraatnaam(straatnaam);
//        locationRepository.save(n);
//        return "Saved!";
//    }
//
//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<Location> getAllLocations() {
//        return locationRepository.findAll();
//    }

    @GetMapping("/location/index")
    public String showLocatieList(Model model) {
        model.addAttribute("location", locationRepository.findAll());
        return "locatie/index";
    }

    @GetMapping("/location/locations")
    public String showLocatieForm(Location location) {
        return "locatie/add-locatie";
    }

    @PostMapping("/location/addlocatie")
    public String addLocatie(Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "locatie/add-locatie";
        }

        locationRepository.save(location);
        return "redirect:/location/index";
    }

    @GetMapping("/location/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Verkeerde locatie ID:" + id));

        model.addAttribute("location", location);
        return "locatie/update-locatie";
    }

    @PostMapping("/location/update/{id}")
    public String updateLocatie(@PathVariable("id") long id, Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            location.setId(id);
            return "locatie/update-locatie";
        }

        locationRepository.save(location);
        return "redirect:/location/index";
    }

    @GetMapping("/location/delete/{id}")
    public String deleteLocatie(@PathVariable("id") long id, Model model) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Verkeerde locatie ID:" + id));
        locationRepository.delete(location);
        return "redirect:/location/index";
    }
}