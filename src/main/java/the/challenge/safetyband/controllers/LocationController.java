package the.challenge.safetyband.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import the.challenge.safetyband.domain.Location;
import the.challenge.safetyband.repositories.LocationRepository;

@Controller
@RequestMapping(path = "/location")
public class LocationController {
    @Autowired
    private LocationRepository locationRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewLocation (@RequestParam String breedtegraad, String lengtegraad, String land, String provincie, String stad, String postcode, String huisnummer, String straatnaam) {
        Location n = new Location();
        n.setBreedtegraad(breedtegraad);
        n.setLengtegraad(lengtegraad);
        n.setLand(land);
        n.setProvincie(provincie);
        n.setStad(stad);
        n.setPostcode(postcode);
        n.setHuisnummer(huisnummer);
        n.setStraatnaam(straatnaam);
        locationRepository.save(n);
        return "Saved!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}