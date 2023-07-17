//package com.example.music.post.dto;
//
//import com.example.music.post.entity.Post;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//@Getter
//public class PostRequestDto {
//    private String title;
//    private String content;
//    private String yUrl;
//    private String category;
//
//    public String getyUrl() {
//        return yUrl;
//    }
//
//    public Post toPostEntity() {
//        return Post.builder()
//                .title(title)
//                .content(content)
//                .yUrl(yUrl)
//                .category(category)
//                .build();
//    }
//}
