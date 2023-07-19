package com.example.music.user.controller;

import com.example.music.post.dto.PostDto;
import com.example.music.post.entity.Post;
import com.example.music.user.dto.LoginRequestDto;
import com.example.music.user.dto.MyPageResponseDto;
import com.example.music.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid LoginRequestDto loginRequestDto){
        userService.signup(loginRequestDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/mypage")
    public ResponseEntity<MyPageResponseDto> mypost(@AuthenticationPrincipal UserDetails userDetails){
      return ResponseEntity.ok(userService.findAll(userDetails));
    }
}
