package com.example.music.user.service;

import com.example.music.user.dto.LoginRequestDto;
import com.example.music.user.entity.User;
import com.example.music.user.repository.UserRepository;
import com.example.music.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN = "admin";

    public void signup(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = passwordEncoder.encode(loginRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> findUser = userRepository.findByUsername(username);
        if (findUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        User user = new User(username, password);
        if (user.getUsername().equals(ADMIN)) {
            user.setRoleToAdmin();
        }

        userRepository.save(user);
    }
}
