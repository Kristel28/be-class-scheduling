package com.groupseven.classscheduling.service;

import com.groupseven.classscheduling.model.User;
import com.groupseven.classscheduling.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {


    private final JavaMailSender emailSender;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username was not found!"));
    }

    public String updateResetPasswordToken(String token, String email) throws MessagingException {
        User user = userRepository.findByEmail(email).get();
        if (user != null) {
            user.setResetPasswordToken(token);
            sendEmail(user);
            userRepository.save(user);
            return "Update Reset password token";
        } else {
            throw new IllegalStateException("Could not find any customer with the email " + email);
        }
    }
//ge
    public void updatePassword(User userRequest) {
        User user = userRepository.findByResetPasswordToken(userRequest.getResetPasswordToken());
       if(user != null) {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setResetPasswordToken(null);
           userRepository.save(user);
       } else {
           throw new IllegalStateException("Cannot changed the password for username : " + userRequest.getUsername());
       }

    }

    private void sendEmail(User user) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmail());
        helper.setSubject("Important Wedding Event Notice");

        String emailText = "Dear " + user.getName() + ",\n\nPlease find the attached QR code."
                    + "To confirm your attendance, please click here : "
                    +"http://localhost:8080/api/v1/guests/confirm-account?token="+user.getResetPasswordToken();

        System.out.println("Confirmation Token: " + user.getResetPasswordToken());

        helper.setText(emailText);
        emailSender.send(message);
    }

}