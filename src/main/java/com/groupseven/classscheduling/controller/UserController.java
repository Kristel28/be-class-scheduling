package com.groupseven.classscheduling.controller;

import com.groupseven.classscheduling.model.User;
import com.groupseven.classscheduling.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<User> getUserDetails(Principal principal){
        String username = principal.getName();
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> isAuthenticated(){
        return new ResponseEntity<>("User authenticated", HttpStatus.OK);
    }


}