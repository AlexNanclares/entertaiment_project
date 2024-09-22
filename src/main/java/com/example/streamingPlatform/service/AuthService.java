package com.example.streamingPlatform.service;

import com.example.streamingPlatform.DTO.AuthResponse;
import com.example.streamingPlatform.DTO.UserInLoginDTO;
import com.example.streamingPlatform.DTO.UserInRegisterDTO;
import com.example.streamingPlatform.jwt.JwtService;
import com.example.streamingPlatform.persistence.entity.User;
import com.example.streamingPlatform.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(UserInLoginDTO user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        UserDetails userDetail = userRepository.findByUsername(user.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetail);

        AuthResponse resultado = new AuthResponse();
        resultado.setToken(token);

        return resultado;
    }

    public AuthResponse register(UserInRegisterDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCreationDate(LocalDateTime.now());

        userRepository.save(user);

        AuthResponse resultado = new AuthResponse();
        resultado.setToken(jwtService.getToken(user));

        return resultado;
    }
}
