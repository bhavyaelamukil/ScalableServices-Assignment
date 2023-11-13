package com.assignment.controller;

import com.assignment.dto.DispatchDetails;
import com.assignment.dto.EmailDetails;
import com.assignment.dto.Register;
import com.assignment.dto.User;
import com.assignment.producer.MessageProducer;
import com.assignment.services.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@Slf4j
public class RegistrationController {

    private final UserRepository repository;

    private final MessageProducer messageProducer;

    private final ObjectMapper objectMapper;

    @Autowired
    @Qualifier("dispatch") WebClient dispatchWebClient;

    @Autowired
    @Qualifier("email") WebClient emailWebClient;


    public RegistrationController(UserRepository repository, MessageProducer messageProducer, ObjectMapper objectMapper) {
        this.repository = repository;
        //this.messageProducer = messageProducer;
        this.messageProducer = messageProducer;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public void test(){
        System.out.println("working");
    }

    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody Register register) throws JsonProcessingException {
        log.info("Create user {}" , register);

        User newUser = new User();
        newUser.setEmail(register.getEmail());
        newUser.setUserName(register.getUserName());

        newUser = repository.save(newUser);

        DispatchDetails details = new DispatchDetails();
        details.setUserId(newUser.getId());
        details.setEmail(register.getEmail());
        details.setPhone(register.getPhone());
        details.setAddress(register.getAddress());

        String dispatchAsMessage = objectMapper.writeValueAsString(details);

        messageProducer.sendMessage("register" , dispatchAsMessage);

//        DispatchDetails dispatchResponse = dispatchWebClient
//                .post()
//                .uri("/create")
//                .bodyValue(details)
//                .retrieve()
//                .bodyToMono(DispatchDetails.class)
//                .block();

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setToEmail(register.getEmail());
        emailDetails.setUserId(newUser.getId());

        String emailAsMessage = objectMapper.writeValueAsString(emailDetails);

//        EmailDetails emailResponse = emailWebClient
//                .post()
//                .uri("/email")
//                .bodyValue(emailDetails)
//                .retrieve()
//                .bodyToMono(EmailDetails.class)
//                .block();

        messageProducer.sendMessage("notify" , emailAsMessage);

        return ResponseEntity.ok().body("Message sent successfully");

    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> userList(){
        log.info("list all registered users");
        List<User> options = repository.findAll();
        return ResponseEntity.ok(options);
    }

}
