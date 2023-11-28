package com.groupseven.classscheduling.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @GetMapping
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Test controller working", HttpStatus.OK);
    }
}
