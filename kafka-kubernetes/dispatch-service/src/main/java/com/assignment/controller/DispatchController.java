package com.assignment.controller;

import com.assignment.dto.DispatchDetails;
import com.assignment.repo.DispatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/v1/disp")
public class DispatchController {

    private final DispatchRepository repository;

    @GetMapping("/")
    public void test(){
        System.out.println("working");
    }

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok("v1");
    }


    @PostMapping("/create")
    public ResponseEntity<DispatchDetails> createDispatch(@RequestBody DispatchDetails details){

        log.info("new dispatch request for id {} " , details.getUserId());
        DispatchDetails createdDispatch = repository.save(details);
        return ResponseEntity.ok(createdDispatch);

    }

    @GetMapping("/list")
    public ResponseEntity<List<DispatchDetails>> getDispatchList(){
        return ResponseEntity.ok(repository.findAll());
    }


}
