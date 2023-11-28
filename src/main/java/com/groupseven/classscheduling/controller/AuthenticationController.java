package com.groupseven.classscheduling.controller;

import com.groupseven.classscheduling.jwt.JwtProvider;
import com.groupseven.classscheduling.model.User;
import com.groupseven.classscheduling.model.request.SigninDto;
import com.groupseven.classscheduling.model.response.AuthenticationResponseDto;
import com.groupseven.classscheduling.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>("User successfully created", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponseDto> signin(@RequestBody SigninDto signinDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinDto.getUsername(), signinDto.getPassword()));

        log.info("Authentication : {}", authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new ResponseEntity<>(new AuthenticationResponseDto(token), HttpStatus.OK);
    }


}
