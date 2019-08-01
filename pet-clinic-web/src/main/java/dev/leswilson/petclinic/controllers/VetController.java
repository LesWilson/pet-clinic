package dev.leswilson.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {
    @GetMapping(value={"/vets", "/vets/index", "/vets/index.html"})
    public String index() {
        return "vets/index";
    }
}
