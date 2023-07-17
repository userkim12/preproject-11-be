package com.example.music.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}")
    private String password;

//    @Bean("jasyptStringEncryptor")
//    public StringEncryptor stringEncryptor() {
//
//    }
}
