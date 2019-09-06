package dev.leswilson.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value={"","/", "/index", "/index.html"})
    public String index() {
        return "index";
    }

    @GetMapping("oops")
    public String oops() {
        return "error";
    }
}
