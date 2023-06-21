package com.iqjoy.csofaq.controllers;

import com.iqjoy.csofaq.entities.CSOFAQs;
import com.iqjoy.csofaq.entities.NewFAQRequest;
import com.iqjoy.csofaq.repository.CSOFAQsRepository;
import com.iqjoy.csofaq.repository.NewFAQRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/faq_requests")
public class FAQRequestEnd {
    @Autowired
    private CSOFAQsRepository csofaQsRepository;

    @Autowired
    private NewFAQRequestRepository newFAQRequestRepository;

    @PostMapping("/existing_faq")
    public List<CSOFAQs> getAllChangeRequests(@RequestBody Map<String, String> request) {
        System.out.println("getAllChangeRequests: "+request);
        String username = request.get("username");
        String password = request.get("password");

        if(username.equals("admin") && password.equals("password")){
            return csofaQsRepository.findAll();
        }else return new ArrayList<CSOFAQs>();
    }

    @GetMapping("/requests")
    public List<NewFAQRequest> getAllNewRequests() {
        System.out.println("getting ALL ");
        return newFAQRequestRepository.findAll();
    }

    @PostMapping("/save_response")
    public List<CSOFAQs> saveResponse(@RequestBody Map<String, String> request) {
        System.out.println("saving CSOs: "+request);
        String response = request.get("response");
        String question = request.get("question");
        String regulator = request.get("regulator");

        long id = Long.parseLong(request.get("id"));

        NewFAQRequest newFAQRequest = newFAQRequestRepository.getReferenceById(id);
        newFAQRequest.setQuestion(question);
        //save this object
        CSOFAQs csofaQs = new CSOFAQs();
        csofaQs.setResponse(response);
        csofaQs.setQuestion(question);
        csofaQs.setRegulator(regulator);
        csofaQs.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        csofaQsRepository.save(csofaQs);

        return csofaQsRepository.findAll();
    }

    @PostMapping("/delete_faq")
    public List<CSOFAQs> deleteChangeRequests(@RequestBody Map<String, String> request) {
        System.out.println("Deleted id: "+request);
        String username = request.get("username");
        String password = request.get("password");
        int id = Integer.parseInt(request.get("id").trim());

        if(username.equals("admin") && password.equals("password")){
            csofaQsRepository.deleteById(id);
            System.out.println("Deleted id: "+id);
            return csofaQsRepository.findAll();
        }else return new ArrayList<CSOFAQs>();
    }

    @PostMapping("/delete_faq_request")
    public List<NewFAQRequest> deleteFAQRequests(@RequestBody Map<String, String> request) {
        System.out.println("Deleted id: "+request);
        String username = request.get("username");
        String password = request.get("password");
        Long id = Long.parseLong(request.get("id").trim());

        if(username.equals("admin") && password.equals("password")){
            newFAQRequestRepository.deleteById(id);
            System.out.println("Deleted id: "+id);
            return newFAQRequestRepository.findAll();
        }else return new ArrayList<NewFAQRequest>();
    }

    @PostMapping("/mail_sent_approval")
    public List<NewFAQRequest> mailFAQResponse(@RequestBody Map<String, String> request) {
        System.out.println("mailFAQResponse approving email id: "+request);
        String username = request.get("username");
        String password = request.get("password");
        Long id = Long.parseLong(request.get("id").trim());

        if(username.equals("admin") && password.equals("password")){
            NewFAQRequest newFAQRequest = newFAQRequestRepository.getReferenceById(id);
            newFAQRequest.setApproved(true);
            newFAQRequestRepository.save(newFAQRequest);
            System.out.println("saved approval for: "+id);
            return newFAQRequestRepository.findAll();
        }else return new ArrayList<NewFAQRequest>();
    }
}
