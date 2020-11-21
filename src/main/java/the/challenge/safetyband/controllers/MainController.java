package the.challenge.safetyband.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @RequestMapping("/")
    public String home() {
        return "home/index";
    }
}
