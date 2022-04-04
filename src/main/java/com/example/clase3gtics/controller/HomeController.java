package com.example.clase3gtics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/2")
    public String inde2x(){
        return "index";
    }

    @GetMapping("/4")
    public String index4(){
        return "index";
    }
}
