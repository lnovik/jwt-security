package com.lnovik.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {


    @GetMapping("/status")
    public String viewStatus(){
        return "online";
    }



}
