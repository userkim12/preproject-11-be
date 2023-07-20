package com.example.music.comment.entity;

import com.example.music.audit.BaseTimeEntity;
import com.example.music.comment.dto.CommentRequestDto;
import com.example.music.post.entity.Post;
import com.example.music.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    @ManyToOne
    private User user;

    public void connectPost(Post post) {
        this.post = post;
    }

    public void connectUser(User user) {
        this.user = user;
    }
}
