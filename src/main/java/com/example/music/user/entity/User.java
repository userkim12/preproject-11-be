package com.example.music.user.entity;

import com.example.music.comment.entity.Comment;
import com.example.music.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Post> postList = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setRoleToAdmin() {
        this.role = Role.ADMIN;
    }
}