package com.example.clase3gtics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("/3")
    public String index3(){
        return "index";
    }

    @GetMapping("/hola")
    @ResponseBody
    public String hola(){
        return "Percy dice hola";
    }

}
