package com.example.Application.security.controller;

import com.example.Application.model.Admin;
import com.example.Application.security.dto.*;
import com.example.Application.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
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
    @GetMapping("/verify/{code}")
    public String verifyPage(){
        return "need to be implemented";
    }
    @PostMapping("/verify")
    public VerifyResponseDTO verify(@RequestBody AccountVerificationDTO verificationDTO){
        return authenticationService.accountVerification(verificationDTO);
    }

    @PostMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        return new ResponseEntity<>(authenticationService.changePassword(changePasswordDTO),HttpStatus.OK);
    }
}
