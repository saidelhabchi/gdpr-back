package com.example.Application.security.controller;

import com.example.Application.model.Admin;
import com.example.Application.security.dto.AdminRegisterDTO;
import com.example.Application.security.dto.LoginRequestDTO;
import com.example.Application.security.dto.LoginResponseDTO;
import com.example.Application.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody AdminRegisterDTO registerDTO){
        Admin admin = authenticationService.registration(registerDTO);
        return new ResponseEntity<>(admin,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO){
        LoginResponseDTO res = authenticationService.login(requestDTO);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
