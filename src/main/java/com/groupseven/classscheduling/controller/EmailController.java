package com.groupseven.classscheduling.controller;

import com.groupseven.classscheduling.model.request.ContactFormDto;
import com.groupseven.classscheduling.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/emails")
@CrossOrigin(origins = "http://localhost:4200")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<ContactFormDto> sendEmail(@RequestBody ContactFormDto contactFormDto) throws MessagingException {
        return new ResponseEntity<>(emailService.sendEmail(contactFormDto), HttpStatus.OK);
    }
}
