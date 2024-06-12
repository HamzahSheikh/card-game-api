package com.project.cardgame.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardGameController {


    // Test endpoint
    @RequestMapping("/test")
    public String test() {

        return "This is the Card Game REST API! Hello World!";
    }

}