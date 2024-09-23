package com.example.streamingPlatform.service;

import com.example.streamingPlatform.DTO.AuthResponse;
import com.example.streamingPlatform.DTO.UserInLoginDTO;
import com.example.streamingPlatform.DTO.UserInRegisterDTO;
import com.example.streamingPlatform.exceptions.BadRequestException;
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

import static com.example.streamingPlatform.util.Utilities.buildClaimsUser;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(UserInLoginDTO user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        User userDetail = userRepository.findByUsername(user.getUsername()).orElseThrow();
        String token = jwtService.getToken(userDetail, buildClaimsUser(userDetail));

        return new AuthResponse(token);
    }

    public AuthResponse register(UserInRegisterDTO userDTO) {

        if(!userRepository.findByUsername(userDTO.getUsername()).isEmpty()){
            throw new BadRequestException("Ya existe un usuario con el username ::: " + userDTO.getUsername() + ". Por favor intenta con otro.");
        }

        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCreationDate(LocalDateTime.now());

        userRepository.save(user);

        String token = jwtService.getToken(user, buildClaimsUser(user));

        return new AuthResponse(token);
    }
}
