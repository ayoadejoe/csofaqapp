package com.iqjoy.cso.controllers;

import com.iqjoy.cso.models.HomeModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        HomeModel homeModel = new HomeModel("Hello, World!");
        model.addAttribute("homeModel", homeModel);
        return "home2";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        HomeModel homeModel = new HomeModel("Hello, World!");
        model.addAttribute("homeModel", homeModel);
        return "faq";
    }
}
