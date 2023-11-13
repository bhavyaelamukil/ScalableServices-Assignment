package com.assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/reg/index")
public class IndexController {

    /**
     * Default landing page.
     */
    @GetMapping()
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Started Register-service");
    }
}
