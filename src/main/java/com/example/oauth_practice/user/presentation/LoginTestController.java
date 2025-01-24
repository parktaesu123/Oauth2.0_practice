package com.example.oauth_practice.user.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginTestController {
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error) {
        if (error != null) {
            // 로그인 실패 처리
        }
        return "login"; // login.html 페이지 리턴
    }


    @GetMapping("/home")
    public String homePage() {
        return "home"; // home.html 템플릿을 반환
    }

}
