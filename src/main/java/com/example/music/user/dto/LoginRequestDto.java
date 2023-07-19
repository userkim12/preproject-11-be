package com.example.music.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {

//    @Pattern(regexp = "^[a-zA-Z0-9].{10,25}$")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]{4,6}\\.[a-zA-Z]{2,5}$")
    private String username;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,15}$", message = "최소 8자이상, 15자 이하이며 알파벳 소문자, 대문자, 숫자 (0~9) 하나 필수, 문자(@#$%^&+=!) 하나 필수")
    private String password;
}