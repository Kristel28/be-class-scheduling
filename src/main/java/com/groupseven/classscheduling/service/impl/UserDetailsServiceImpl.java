package com.groupseven.classscheduling.service.impl;

import com.groupseven.classscheduling.model.User;
import com.groupseven.classscheduling.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException("Username : " + username + " was not found!")
//        );
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), getRoles(user.getRole()));
//    }
//
//
//    List<GrantedAuthority> getRoles(Set<String> roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority("USER"))
//                .collect(Collectors.toList());
//    }
//}