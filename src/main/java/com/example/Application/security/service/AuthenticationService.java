package com.example.Application.security.service;

import com.example.Application.model.Admin;
import com.example.Application.repository.AdminRepository;
import com.example.Application.security.dto.AdminRegisterDTO;
import com.example.Application.security.dto.LoginRequestDTO;
import com.example.Application.security.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    public Admin registration(AdminRegisterDTO registerDTO){
        if(adminRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            return null;
        }
        Admin newAdmin = new Admin();
        newAdmin.setUsername(registerDTO.getUsername());
        newAdmin.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newAdmin.setFirstName(registerDTO.getFirstname());
        newAdmin.setLastName(registerDTO.getLastname());
        newAdmin.setEmail(registerDTO.getEmail());
        newAdmin.setPhone(registerDTO.getPhone());
        return adminRepository.save(newAdmin);
    }
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenService.generateToken(authentication);
            return new LoginResponseDTO(true,token);
        }catch (Exception e){
            return new LoginResponseDTO(false,"");
        }
    }
}
