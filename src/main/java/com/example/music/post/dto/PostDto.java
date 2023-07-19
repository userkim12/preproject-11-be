package com.example.music.post.dto;

import com.example.music.comment.dto.CommentResponseDto;
import com.example.music.comment.entity.Comment;
import com.example.music.post.entity.CategoryEnum;
import com.example.music.post.entity.Post;
import com.example.music.user.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


public class PostDto {
    @Getter
    public static class Request {
        private String title;
        private String content;
        private String yUrl;
        private String category;

        public String getyUrl() {
            return yUrl;
        }

        public Post toPostEntity(User user) {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .yUrl(yUrl)
                    .category(CategoryEnum.nameOf(category))
                    .user(user)
                    .build();
        }
    }
    @Getter
//    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private Long postId;
        private String title;
        private String content;
        private String category;
        private String yUrl;
        private String username;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<CommentResponseDto> commentList;
        private int likes;


        private Response(Post post) {
            this.postId = post.getPostId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.category = post.getCategory().getEnglishName();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
            this.commentList = post.getCommentList() == null ? null : post.getCommentList().stream().map(CommentResponseDto::of).toList();
            this.yUrl = post.getYUrl();
            this.likes = post.getLikes();
            this.username = post.getUser().getUsername();
        }

        @Builder
        private Response(String username, Long postId, String title, String content, String category, String yUrl, LocalDateTime createdAt, LocalDateTime modifiedAt, int likes) {
            this.postId = postId;
            this.title = title;
            this.content = content;
            this.category = category;
            this.yUrl = yUrl;
            this.createdAt = createdAt;
            this.modifiedAt = modifiedAt;
            this.likes = likes;
            this.username = username;
        }

        public static Response of(Post post) {
            return new Response(post);
        }

        public static Response nonCommentOf(Post post) {
            return Response.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory().getEnglishName())
                    .yUrl(post.getYUrl())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .likes(post.getLikes())
                    .username(post.getUser().getUsername())
                    .build();
        }
    }
}
