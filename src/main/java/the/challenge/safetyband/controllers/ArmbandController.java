package the.challenge.safetyband.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import the.challenge.safetyband.domain.Armband;
import the.challenge.safetyband.repositories.ArmbandRepository;

@Controller
public class ArmbandController {

    private final ArmbandRepository armbandRepository;

    @Autowired
    public ArmbandController(ArmbandRepository armbandRepository) {
        this.armbandRepository = armbandRepository;
    }

    @GetMapping("/index")
    public String showArmbandList(Model model) {
        model.addAttribute("armband", armbandRepository.findAll());
        return "armband/index";
    }

    @GetMapping("/armband")
    public String showArmbandForm(Armband armband) {
        return "armband/add-armband";
    }

    @PostMapping("/addarmband")
    public String addArmband(Armband armband, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "armband/add-armband";
        }

        armbandRepository.save(armband);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Armband armband = armbandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Verkeerde armband ID:" + id));

        model.addAttribute("armband", armband);
        return "armband/update-armband";
    }

    @PostMapping("/update/{id}")
    public String updateArmband(@PathVariable("id") long id, Armband armband, BindingResult result, Model model) {
        if (result.hasErrors()) {
            armband.setId(id);
            return "armband/update-armband";
        }

        armbandRepository.save(armband);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteArmband(@PathVariable("id") long id, Model model) {
        Armband armband = armbandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Verkeerde armband ID:" + id));
        armbandRepository.delete(armband);
        return "redirect:/index";
    }
}
