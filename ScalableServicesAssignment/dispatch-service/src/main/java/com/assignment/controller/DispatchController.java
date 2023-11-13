package com.assignment.controller;

import com.assignment.dto.DispatchDetails;
import com.assignment.repo.DispatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DispatchController {

    private final DispatchRepository repository;


    public DispatchController( DispatchRepository repository) {
        this.repository = repository;
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
