package the.challenge.safetyband.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @RequestMapping("/")
    public String home() {
        return "home/index";
    }

    @GetMapping("/prijzen")
    public String prijzen() {
        return "home/prijzen";
    }

    @RequestMapping("/over-ons")
    public String overOns() {
        return "home/over-ons";
    }

    @RequestMapping("/faq")
    public String faq() {
        return "home/faq";
    }
}
