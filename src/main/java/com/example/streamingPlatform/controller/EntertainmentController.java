package com.example.streamingPlatform.controller;

import com.example.streamingPlatform.persistence.entity.Entertainment;
import com.example.streamingPlatform.service.EntertainmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entertainment")
@RequiredArgsConstructor
public class EntertainmentController {

    private final EntertainmentService entertainmentService;

    @GetMapping("/random")
    public ResponseEntity<Entertainment> getRandomEntertaiment(){
        return ResponseEntity.ok(entertainmentService.getRandomEntertaiment());
    }

    @GetMapping("/order")
    public ResponseEntity<List<Entertainment>> getEntertaimentByOrder(@RequestParam String typeOrder){
        return ResponseEntity.ok(entertainmentService.getEntertaimentByOrder(typeOrder));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Entertainment>> getEntertaimentByFilter(@RequestParam String typeFilter, @RequestParam String filterValue){
        return ResponseEntity.ok(entertainmentService.getEntertaimentByFilter(typeFilter, filterValue));
    }
}
