package com.college.yi.bookmanager.service;


import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.entity.UserEntity;
@Service
public class BookmanagerUserDetailsService implements UserDetailsService{
    private final UserService userService;
    
    public BookmanagerUserDetailsService (UserService userService){
    this.userService = userService;
}
    
    @Override
    public UserDetails loadUserByUsername(String username) throws  UsernameNotFoundException {
        UserEntity user = userService.getUserByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        String role = "ROLE_" + user.getRole().toUpperCase().trim();
        
    return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .authorities(List.of(new SimpleGrantedAuthority(role)))
            .disabled(!user.isEnabled())
            .build();
}
}



  