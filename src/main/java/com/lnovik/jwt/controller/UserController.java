package com.lnovik.jwt.controller;

import com.lnovik.jwt.data.UserData;
import com.lnovik.jwt.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/all-views")
    public List<UserData> listAllUsers(){
        return userDetailsService.listUsers();
    }



}
