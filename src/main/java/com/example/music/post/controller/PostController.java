package com.example.music.post.controller;

import com.example.music.post.dto.PostRequestDto;
import com.example.music.post.dto.PostResponseDto;
import com.example.music.post.entity.Post;
import com.example.music.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<PostResponseDto> post(@RequestBody PostRequestDto postRequestDto) {
        Post post = postService.createPost(postRequestDto);

        return ResponseEntity.ok(PostResponseDto.of(post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> put(@PathVariable Long postId,
                              @RequestBody PostRequestDto postRequestDto) {
//        Post post = postService.updatePost(postId, postRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity get(@PathVariable Long postId) {
        Post findPost = postService.findBoard(postId);

        return ResponseEntity.ok(PostResponseDto.of(findPost));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAll() {
        List<Post> postList = postService.findAllBoards();


        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity delete(@PathVariable Long postId) {
//        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postTitle}")
    public ResponseEntity search(@PathVariable String postTitle) {
        List<Post> postList = postService.searchPost(postTitle);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity likeUp(@PathVariable Long postId) {
        postService.likeUp(postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}/likes")
    public ResponseEntity likeDown(@PathVariable Long postId) {
        postService.likeDown(postId);
        return ResponseEntity.ok().build();
    }

}
