package com.example.music.comment.controller;

import com.example.music.comment.dto.CommentRequestDto;
import com.example.music.comment.dto.CommentResponseDto;
import com.example.music.comment.entity.Comment;
import com.example.music.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("postId") Long postId,
                                                            @AuthenticationPrincipal UserDetails userDetails,
                                                            @RequestBody CommentRequestDto commentRequestDto){
        // 여기 수정중
        Comment comment = commentService.createComment(userDetails, commentRequestDto);
        return ResponseEntity.ok(CommentResponseDto.of(comment));
    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable("commentId") Long commentId){
        commentService.deleteComment(userDetails, commentId);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        //body 없이 !
    }



}
