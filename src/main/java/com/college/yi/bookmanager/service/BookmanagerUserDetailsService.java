package com.college.yi.bookmanager.service;


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
        
    return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .disabled(!user.isEnabled()) .build();
}
}



  