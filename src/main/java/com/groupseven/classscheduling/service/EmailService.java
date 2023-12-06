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

    private final String CONTACT_SUPPORT_EMAIL = "systemscheduling281@gmail.com";

    public ContactFormDto sendEmail(ContactFormDto contactFormDto) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setReplyTo(contactFormDto.getEmail());
        helper.setTo(contactFormDto.getEmail());
        helper.setSubject("Class scheduling support");
        String emailText =
                "Dear "+ contactFormDto.getFirstName() + " " + contactFormDto.getLastName()+",\n\n"
                + "I hope this message finds you well. This is the contact support of System Scheduling System,\n"
                + "and I am reaching out to address a concern related to our scheduling system.\n\n"
                + "It has come to our attention that some users, including yourself, may be experiencing \n"
                + "difficulties with the scheduling system. We understand the importance of a seamless \n"
                + "scheduling process, and we want to ensure that you have the best experience possible.\n\n"

                + "To help us assist you more efficiently, could you please provide additional details about\n"
                + "the problem you are facing? Any specific error messages, unexpected behaviors, or \n"
                + "relevant context will greatly aid our support team in identifying and addressing the issue.\n\n"

                + "We apologize for any inconvenience this may have caused and appreciate your \n"
                + "patience as we work to resolve this matter. Rest assured, our team is dedicated to \n"
                + "ensuring that the scheduling system operates smoothly for all users.\n\n"

                + "If you could share more information at your earliest convenience, it would greatly assist \n"
                + "us in expediting the resolution process. You can respond directly to this email, or if you \n"
                + "prefer, you may contact our support team at systemscheduling281@gmail.com.\n\n"

                + "Thank you for bringing this to our attention, and we appreciate your understanding as \n"
                + "we work towards a swift resolution.\n\n"

                + "Best regards,\n"
                + "System scheduling Management System\n"
                ;
        helper.setText(emailText);

        emailSender.send(message);
        return contactFormDto;
    }

}
