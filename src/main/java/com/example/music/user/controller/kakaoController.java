package com.example.music.user.controller;

import com.example.music.user.service.KakaoService;
import com.example.music.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class kakaoController {
    private final KakaoService kakaoService;
    @GetMapping("/api/user/kakao/callback")
    public String getKakaoToken(@RequestParam String code, HttpServletResponse response) throws JsonProcessingException {

        String token = kakaoService.getKakaoToken(code).substring(7);
        Cookie cookie = new Cookie(JwtUtil.AUTHORIZATION_HEADER, token);
        response.addCookie(cookie);

        return token;
    }
}
