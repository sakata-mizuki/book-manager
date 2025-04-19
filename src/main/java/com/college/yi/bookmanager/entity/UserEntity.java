package com.college.yi.bookmanager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;

}
