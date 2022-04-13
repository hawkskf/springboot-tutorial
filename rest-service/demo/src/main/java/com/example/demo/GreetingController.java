package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
//    @GetMapping("/greeting")
//    public String Greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

    @GetMapping("/greeting")
    public String Greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("greeting", new Greeting(0,""));
        return "greeting";
    }

    @PostMapping("/greeting")
    public String GreetingSubmit(@ModelAttribute Greeting greeting,Model model) {
        model.addAttribute("greeting", greeting);
        return "result";
    }
}
