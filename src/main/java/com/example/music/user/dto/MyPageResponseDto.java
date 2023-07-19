package com.example.music.user.dto;

import com.example.music.post.dto.PostDto;
import com.example.music.post.entity.Post;
import com.example.music.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MyPageResponseDto {
    private String username;
    List<Post> responseList;

    public MyPageResponseDto(User user) {
        this.username = user.getUsername();
        this.responseList = user.getPostList();
    }
}
