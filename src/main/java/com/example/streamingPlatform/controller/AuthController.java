package com.example.streamingPlatform.controller;

import com.example.streamingPlatform.DTO.AuthResponse;
import com.example.streamingPlatform.DTO.UserInLoginDTO;
import com.example.streamingPlatform.DTO.UserInRegisterDTO;
import com.example.streamingPlatform.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserInLoginDTO user){
       return ResponseEntity.ok(authService.login(user));
   }

   @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserInRegisterDTO user){
       return ResponseEntity.ok(authService.register(user));
   }
}

