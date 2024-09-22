package com.example.streamingPlatform.controller;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import com.example.streamingPlatform.service.EntertainmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entertainment")
@RequiredArgsConstructor
public class EntertainmentController {

    private final EntertainmentService entertainmentService;

    @GetMapping("/random")
    public ResponseEntity<Entertainment> getRandomEntertaiment(){
        return ResponseEntity.ok(entertainmentService.getRandomEntertaiment());
    }
}
