package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        
        System.out.println("Example log from std out.");
        System.out.println("Another example log from std out!");

        return "welcome"; //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {

        model.addAttribute("message", name);
        
        System.out.println("Hello controller, name = "+name); 

        return "welcome"; //view
    }

    @GetMapping("/health")
    public String health() {
        Date date = new Date();
        System.out.println("** Request received on /health at "+date.toString()); 

        return "welcome";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("Hello from the backend!", message);

        return "test";
    }
}
