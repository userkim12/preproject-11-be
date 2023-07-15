package com.example.music.comment.controller;

import com.example.music.comment.dto.CommentRequestDto;
import com.example.music.comment.dto.CommentResponseDto;
import com.example.music.comment.entity.Comment;
import com.example.music.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId, @RequestBody CommentRequestDto commentRequestDto){
        Comment comment = commentService.createComment(commentRequestDto);
        return ResponseEntity.ok(CommentResponseDto.of(comment));
    }

    /*
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity deleteComment(){
    }
    */


}
