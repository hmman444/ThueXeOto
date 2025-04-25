package com.hcmute.thuexe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcmute.thuexe.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {
        String username = authentication.getName(); // Lấy username từ token đã giải mã
        return userService.getProfileByUsername(username);
    }

    

}

