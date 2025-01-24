package com.example.oauth_practice.user.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html 템플릿을 반환
    }

    @GetMapping("/home")
    public String homePage() {
        return "home"; // home.html 템플릿을 반환
    }

}
