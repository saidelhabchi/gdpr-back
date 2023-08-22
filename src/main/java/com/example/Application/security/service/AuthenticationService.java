package com.example.Application.security.service;

import com.example.Application.model.Admin;
import com.example.Application.repository.AdminRepository;
import com.example.Application.security.dto.*;
import com.example.Application.service.mail.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {
    final String LINK = "http://localhost:8800/api/auth/verify";
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Autowired
    MailService mailService;
    @Value("${gdpr.env.admin.mail}")
    String adminMail;
    public Admin registration(AdminRegisterDTO registerDTO){
        if(adminRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            //need to implement the code for the case where duplicated usernames
            return null;
        }
        Admin newAdmin = new Admin();
        newAdmin.setUsername(registerDTO.getUsername());
        newAdmin.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newAdmin.setFirstName(registerDTO.getFirstname());
        newAdmin.setLastName(registerDTO.getLastname());
        newAdmin.setEmail(registerDTO.getEmail());
//        newAdmin.setPhone(registerDTO.getPhone());
        //added for email verification
        String uuid_verification = UUID.randomUUID().toString();
        newAdmin.setVerificationCode(uuid_verification);
        newAdmin.setEnabled(false);
        sendVerificationMail(adminMail,newAdmin.getEmail(), LINK,uuid_verification,newAdmin.getId());
        return adminRepository.save(newAdmin);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Optional<Admin> adminOptional = adminRepository.findByEmail(loginRequestDTO.getEmail());
        if(adminOptional.isPresent()){
            Admin admin = adminOptional.get();
            if(admin.isEnabled()){
                try {
                    Authentication authentication = authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(admin.getUsername(),loginRequestDTO.getPassword())
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    String token = tokenService.generateToken(authentication);
                    return new LoginResponseDTO(true,token,"none");
                }catch (Exception e){
                    return new LoginResponseDTO(false,"","credentials are not correct");
                }
            }else{
                sendVerificationMail(adminMail, admin.getEmail(),LINK,admin.getVerificationCode(), admin.getId());
                return new LoginResponseDTO(false,"","account is disabled");
            }
        }else{
            return new LoginResponseDTO(false,"","you don't have an account! sign up first.");
        }
    }
    public VerifyResponseDTO accountVerification(AccountVerificationDTO accountVerificationDTO){
        Admin admin = adminRepository.findById(accountVerificationDTO.getId()).get();
         if(accountVerificationDTO.getVerification_code().equals(admin.getVerificationCode())){
             admin.setEnabled(true);
             admin.setVerificationCode(null);
             adminRepository.save(admin);
            return new VerifyResponseDTO("success", true);
        }
        else{
            return new VerifyResponseDTO("failed", false);
        }
    }

    public String changePassword(ChangePasswordDTO changePasswordDTO){
        Optional<Admin> toBeChanged = adminRepository.findByEmail(changePasswordDTO.getEmail());
        if(toBeChanged.isPresent()){
            Admin admin = toBeChanged.get();
            if(passwordEncoder.matches(changePasswordDTO.getOld_password(),admin.getPassword())){
                String password_encoded = passwordEncoder.encode(changePasswordDTO.getNew_password());
                admin.setPassword(password_encoded);
                adminRepository.save(admin);
                return "changed with success";
            }else{
                return "password incorrect";
            }
        }
        return "this account doesn't exist";
    }
    private String sendVerificationMail(String to,String newAdmin, String link,String verification_code,int id){
        try{
            String content = "to verify "+ newAdmin +" account click in this link : "+link+"/"+id+" \n"+"please enter this "+ verification_code+"\n";
            mailService.sendEmail(to,"Account verification",content);
            return "mail sent successfully";
        } catch (MessagingException e) {
            return "mail failed due to something";
        }
    }
}