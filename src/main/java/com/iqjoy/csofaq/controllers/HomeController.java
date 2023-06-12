package com.iqjoy.csofaq.controllers;

import com.iqjoy.csofaq.entities.CSOFAQs;
import com.iqjoy.csofaq.entities.NewFAQRequest;
import com.iqjoy.csofaq.models.HomeModel;
import com.iqjoy.csofaq.repository.CSOFAQsRepository;
import com.iqjoy.csofaq.repository.NewFAQRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Component
@Controller
@CrossOrigin(origins = "https://www.csocompliance.org.ng")
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
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String submitForm(@RequestParam("email") String email, @RequestParam("question") String question) {
        System.out.println(email +" received");
        System.out.println(question +" received");
        NewFAQRequest contact = new NewFAQRequest();
        contact.setEmail(email);
        contact.setQuestion(question);
        contact.setCreated_at(new Timestamp(System.currentTimeMillis()));
        newFAQRequestRepository.save(contact);
        System.out.println("Saved!");

        // Save data to database here
        String message = "Thank you. We would respond to you soon";
        return "{\"message\":\"" + message + "\"}";
    }
}
