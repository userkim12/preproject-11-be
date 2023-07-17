//package com.example.music.post.dto;
//
//import com.example.music.comment.entity.Comment;
//import com.example.music.post.entity.Post;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Getter
//public class PostResponseDto {
//    private Long postId;
//    private String title;
//    private String content;
//    private String category;
//    private String yUrl;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
//
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private List<Comment> commentList;
//    private int likes;
//
//
//    private PostResponseDto(Post post) {
//        this.postId = post.getPostId();
//        this.title = post.getTitle();
//        this.content = post.getContent();
//        this.category = post.getCategory();
//        this.createdAt = post.getCreatedAt();
//        this.modifiedAt = post.getModifiedAt();
//        this.commentList = post.getCommentList();
//        this.yUrl = post.getYUrl();
//        this.likes = post.getLikes();
//    }
//
//    @Builder
//    public PostResponseDto(Long postId, String title, String content, String category, String yUrl, LocalDateTime createdAt, LocalDateTime modifiedAt, int likes) {
//        this.postId = postId;
//        this.title = title;
//        this.content = content;
//        this.category = category;
//        this.yUrl = yUrl;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//        this.likes = likes;
//    }
//
//    public static PostResponseDto of(Post post) {
//        return new PostResponseDto(post);
//    }
//}
