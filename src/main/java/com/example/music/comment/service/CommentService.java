package com.example.music.comment.service;

import com.example.music.comment.dto.CommentRequestDto;
import com.example.music.comment.dto.CommentResponseDto;
import com.example.music.comment.entity.Comment;
import com.example.music.comment.repository.CommentRepository;
import com.example.music.post.entity.Post;

import com.example.music.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public Comment createComment(CommentRequestDto commentRequestDto) {

        Post post = findPost(commentRequestDto.getPostId());
        Comment comment = new Comment(commentRequestDto);
        comment.connectPost(post);
        return commentRepository.save(comment);
    }

    public Post findPost(Long id){
        return postRepository.findByPostId(id).orElseThrow(()->
                new IllegalArgumentException("선택하신 게시글은 존재하지 않습니다."));
    }
}
