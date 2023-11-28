package com.groupseven.classscheduling.service;

import com.groupseven.classscheduling.model.request.ContactFormDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private final String CONTACT_SUPPORT_EMAIL = "aaronbujatin@gmail.com";

    public ContactFormDto sendEmail(ContactFormDto contactFormDto) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(CONTACT_SUPPORT_EMAIL);
        helper.setSubject("Class scheduling support");
        String emailText =
                "Name: " + contactFormDto.getFirstName() + " " + contactFormDto.getLastName()
                + "\nEmail: " + contactFormDto.getEmail()
                + "\nMessage: " + contactFormDto.getMessage();
        helper.setText(emailText);

        emailSender.send(message);
        return contactFormDto;
    }

}
