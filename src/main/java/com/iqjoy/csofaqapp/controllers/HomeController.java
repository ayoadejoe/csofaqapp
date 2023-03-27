package com.iqjoy.csofaqapp.controllers;

import com.iqjoy.csofaqapp.entities.CSOFAQs;
import com.iqjoy.csofaqapp.entities.NewFAQRequest;
import com.iqjoy.csofaqapp.models.HomeModel;
import com.iqjoy.csofaqapp.repository.CSOFAQsRepository;
import com.iqjoy.csofaqapp.repository.NewFAQRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class HomeController {

    @Autowired
    private CSOFAQsRepository csofaqsRepository;
    @Autowired
    private NewFAQRequestRepository newFAQRequestRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<String> regulators = csofaqsRepository.findAllRegulators();
        model.addAttribute("regulators", regulators);
        return "home";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        HomeModel homeModel = new HomeModel("Hello, World!");
        model.addAttribute("homeModel", homeModel);
        return "faq";
    }

    @GetMapping("/regulator")
    public String showRegulators(Model model) {
        List<String> regulators = csofaqsRepository.findAllRegulators();
        model.addAttribute("regulators", regulators);
        return "regulators";
    }

    @GetMapping("/regulators/{regulator}")
    public String getQuestionsForRegulator(@PathVariable String regulator, Model model) {
        List<CSOFAQs> faqs = csofaqsRepository.findByRegulator(regulator);
        for(CSOFAQs f : faqs) System.out.println(f.getQuestion());
        model.addAttribute("faqs", faqs);
        model.addAttribute("regulator", regulator);
        return "faqcontents";
    }

    @PostMapping("/submitform")
    public void submitForm(@RequestParam("email") String email, @RequestParam("question") String question) {
        System.out.println(email +" received");
        NewFAQRequest contact = new NewFAQRequest();
        contact.setEmail(email);
        contact.setQuestion(question);
        newFAQRequestRepository.save(contact);
    }
}
