package the.challenge.safetyband.controllers;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import the.challenge.safetyband.domain.WebAdmin;
import the.challenge.safetyband.repositories.WebAdminRepository;

@Controller
@RequestMapping(path = "/admin")
public class WebAdminController {
    @Autowired
    private WebAdminRepository webAdminRepository;
//    private PasswordEncoder passwordEncoder;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewWebAdmin (@RequestParam String gebruikersnaam, String wachtwoord, String email, String naam, String achternaam) {
        WebAdmin n = new WebAdmin();
        n.setGebruikersnaam(gebruikersnaam);
        n.setWachtwoord(wachtwoord);
        n.setEmail(email);
        n.setNaam(naam);
        n.setAchternaam(achternaam);
        webAdminRepository.save(n);
        return "Saved!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<WebAdmin> getAllWebAdmins() {
        return webAdminRepository.findAll();
    }
}
