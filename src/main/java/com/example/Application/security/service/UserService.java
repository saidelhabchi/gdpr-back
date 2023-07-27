package com.example.Application.security.service;

import com.example.Application.model.Admin;
import com.example.Application.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {
    PasswordEncoder passwordEncoder;
    AdminRepository adminRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if (optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            /* Admin admin1 = new Admin();
            admin1.setUsername(admin.getUsername());
            admin1.setFullName(admin.getFullName());
            admin1.setPassword(admin.getPassword());
            admin1.setEmail(admin.getEmail());
            admin1.setPhone(admin.getPhone());
            */
            return admin;
        }
        return null;
    }
}
