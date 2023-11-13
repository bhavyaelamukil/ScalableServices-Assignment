package com.assignment.controller;

import com.assignment.api.TriggerEventsRequest;
import com.assignment.dto.DispatchDetails;
import com.assignment.dto.EmailDetails;
import com.assignment.dto.Register;
import com.assignment.dto.User;
import com.assignment.producer.MessageProducer;
import com.assignment.services.RegisterService;
import com.assignment.services.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/reg")
public class RegistrationController {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final MessageProducer messageProducer;
    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final RegisterService registerService;


    @GetMapping("/")
    public void test(){
        System.out.println("working");
    }

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok("v1");
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody Register register)  {
        log.info("Create user {}" , register);
        System.out.println("create user " + register.toString());

        try {
        User newUser = new User();
        newUser.setEmail(register.getEmail());
        newUser.setUserName(register.getUserName());

        newUser = repository.save(newUser);
        System.out.println("new user created " + newUser.toString());

        DispatchDetails details = new DispatchDetails();
        details.setUserId(newUser.getUserId());
        details.setEmail(register.getEmail());
        details.setPhone(register.getPhone());
        details.setAddress(register.getAddress());
        registerService.process(details);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok().body("Message sent successfully");

    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> userList(){
        log.info("list all registered users");
        List<User> options = repository.findAll();
        return ResponseEntity.ok(options);
    }

}

