package com.example.music.comment.service;

import com.example.music.comment.dto.CommentRequestDto;
import com.example.music.comment.entity.Comment;
import com.example.music.comment.repository.CommentRepository;
import com.example.music.post.entity.Post;

import com.example.music.post.repository.PostRepository;
import com.example.music.user.entity.User;
import com.example.music.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public Comment createComment(UserDetails userDetails, CommentRequestDto commentRequestDto) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new RuntimeException("")
        );
        Post post = findPost(commentRequestDto.getPostId());
        Comment comment = new Comment(commentRequestDto);
        comment.connectPost(post);
        comment.connectUser(user);
        return commentRepository.save(comment);
    }

    public Post findPost(Long id){
        return postRepository.findByPostId(id).orElseThrow(()->
                new IllegalArgumentException("선택하신 게시글은 존재하지 않습니다."));
    }

    public void deleteComment(UserDetails userDetails, Long commentId) {
        String username = userDetails.getUsername();
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new IllegalArgumentException("해당 댓글은 존재하지 않습니다"));
        String author = comment.getUser().getUsername();

        if (!username.equals("admin")) {
            if (!author.equals(username)) {
                throw new RuntimeException("작성자나 관리자만 삭제/수정이 가능합니다.");
            }
        }

        commentRepository.delete(comment);
    }
}
