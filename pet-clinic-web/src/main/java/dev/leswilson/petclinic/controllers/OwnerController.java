package dev.leswilson.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {
    @GetMapping(value={"/owners", "/owners/index", "/owners/index.html"})
    public String index() {
        return "owners/index";
    }
}
