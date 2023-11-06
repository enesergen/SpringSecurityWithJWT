package com.enesergen.demoproject.service;

import com.enesergen.demoproject.model.Role;
import com.enesergen.demoproject.model.User;
import com.enesergen.demoproject.model.dto.UserAuthenticateRequestDto;
import com.enesergen.demoproject.model.dto.UserAuthenticateResponseDto;
import com.enesergen.demoproject.model.dto.UserRegisterRequestDto;
import com.enesergen.demoproject.model.dto.UserRegisterResponseDto;
import com.enesergen.demoproject.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;

    }

    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) throws Exception {
        var user = new User();
        user.setFirstName(userRegisterRequestDto.getFirstName());
        user.setLastName(userRegisterRequestDto.getLastName());
        user.setUsername(userRegisterRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.getPassword()));
        user.setEmail(userRegisterRequestDto.getEmail());
        user.setPhoneNumber(userRegisterRequestDto.getPhoneNumber());
        user.setRole(Role.USER);
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(savedUser);
        return new UserRegisterResponseDto(true,jwtToken,"");
    }

    public UserAuthenticateResponseDto authenticate(UserAuthenticateRequestDto userAuthenticateRequestDto) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthenticateRequestDto.getUsername(),
                userAuthenticateRequestDto.getPassword())
        );
        var user =userRepository.findUserByUsername(userAuthenticateRequestDto.getUsername()).orElseThrow();

        var jwtToken=jwtService.generateToken(user);
        return new UserAuthenticateResponseDto(true,jwtToken,"");

    }


}
