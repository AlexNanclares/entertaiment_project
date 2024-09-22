package com.example.streamingPlatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entertainment")
@RequiredArgsConstructor
public class EntertainmentController {

    @GetMapping("/random")
    public String testEntertainment(){
        return "ESTA ES MI API PROTEGIDA.";
    }
}
