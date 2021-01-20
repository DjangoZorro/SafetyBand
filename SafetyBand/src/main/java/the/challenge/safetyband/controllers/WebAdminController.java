package the.challenge.safetyband.controllers;

//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import the.challenge.safetyband.domain.ConfirmationToken;
import the.challenge.safetyband.domain.WebAdmin;
import the.challenge.safetyband.service.ConfirmationTokenService;
import the.challenge.safetyband.service.WebAdminService;

import java.util.Optional;

@Controller
@AllArgsConstructor
//@RequestMapping(path = "/admin")
public class WebAdminController {
//    @RequestMapping("/login")
//    public String login() {
//        return "webadmin/index";
//    }
//
//    @RequestMapping("/register")
//    public String register() {
//        return "webadmin/register";
//    }
//
//    @Autowired
//    private WebAdminRepository webAdminRepository;
////    private PasswordEncoder passwordEncoder;
//
//    @PostMapping(path = "/add")
//    public @ResponseBody String addNewWebAdmin (@RequestParam String gebruikersnaam, String wachtwoord, String email, String naam, String achternaam) {
//        WebAdmin n = new WebAdmin();
//        n.setGebruikersnaam(gebruikersnaam);
//        n.setWachtwoord(wachtwoord);
//        n.setEmail(email);
//        n.setNaam(naam);
//        n.setAchternaam(achternaam);
//        webAdminRepository.save(n);
//        return "Saved!";
//    }
//
//    @GetMapping(path = "/all")
//    public @ResponseBody Iterable<WebAdmin> getAllWebAdmins() {
//        return webAdminRepository.findAll();
//    }




//    @Autowired
//    private WebAdminRepository webAdminRepository;
//
//    @GetMapping("/register_form")
//    public String registerForm(Model model) {
//        model.addAttribute("webadmin", new WebAdmin());
//        return "webadmin/register_form";
//    }
//
//    @PostMapping("/save")
//    public String saveWebAdmin(@ModelAttribute WebAdmin webAdmin, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "webadmin/register_form";
//        }
//        webAdminRepository.save(webAdmin);
//        model.addAttribute("webadmin", webAdminRepository.findAll());
//        return "home/index";
//    }




    private final WebAdminService webAdminService;

    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-in")
    String signIn() {

        return "webadmin/index";
    }

    @GetMapping("/sign-up")
    String signUpPage(WebAdmin webAdmin) {

        return "webadmin/register_form";
    }

    @PostMapping("/sign-up")
    String signUp(WebAdmin webAdmin) {

        webAdminService.signUpWebAdmin(webAdmin);

        return "redirect:/sign-in";
    }

    @GetMapping("/sign-up/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(webAdminService::confirmWebAdmin);

        return "redirect:/sign-in";
    }
}
