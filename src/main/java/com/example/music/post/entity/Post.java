package com.example.music.post.entity;

import com.example.music.audit.BaseTimeEntity;
import com.example.music.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String yUrl;
    @Column(nullable = false)
    private String content;
    @Column
    private int likes;
    private String category;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    public void update(Post post) {
        Optional.ofNullable(post.getTitle()).ifPresent(title -> this.title = title);
        Optional.ofNullable(post.getYUrl()).ifPresent(yUrl -> this.yUrl = yUrl);
        Optional.ofNullable(post.getContent()).ifPresent(content -> this.content = content);
    }

    public void changLikes(int value) {
        likes = value == 1 ? likes++ : likes--;
    }
}
