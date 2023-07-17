package com.example.music.post.dto;

import com.example.music.comment.entity.Comment;
import com.example.music.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
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

        public Post toPostEntity() {
            return Post.builder()
                    .title(title)
                    .content(content)
                    .yUrl(yUrl)
                    .category(category)
                    .build();
        }
    }
    @Getter
    public static class SingleResponse {
        private Long postId;
        private String title;
        private String content;
        private String category;
        private String yUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<Comment> commentList;
        private int likes;


        private SingleResponse(Post post) {
            this.postId = post.getPostId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.category = post.getCategory();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
            this.commentList = post.getCommentList();
            this.yUrl = post.getYUrl();
            this.likes = post.getLikes();
        }


        public static SingleResponse of(Post post) {
            return new SingleResponse(post);
        }
    }

    @Getter
    public static class WithoutCommentResponse {
        private Long postId;
        private String title;
        private String content;
        private String category;
        private String yUrl;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private int likes;


        private WithoutCommentResponse(Post post) {
            this.postId = post.getPostId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.category = post.getCategory();
            this.createdAt = post.getCreatedAt();
            this.modifiedAt = post.getModifiedAt();
            this.yUrl = post.getYUrl();
            this.likes = post.getLikes();
        }

        public static WithoutCommentResponse of(Post post) {
            return new WithoutCommentResponse(post);
        }
    }
}
