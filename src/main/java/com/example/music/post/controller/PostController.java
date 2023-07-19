package com.example.music.post.controller;

import com.example.music.post.dto.PostDto;
import com.example.music.post.entity.Post;
import com.example.music.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDto.Response> post(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody PostDto.Request postRequestDto) {
        Post post = postService.createPost(userDetails, postRequestDto);

        return ResponseEntity.ok(PostDto.Response.of(post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto.Response> put(@AuthenticationPrincipal UserDetails userDetails,
                                                @PathVariable Long postId,
                                                @RequestBody PostDto.Request postRequestDto) {
        Post post = postService.updatePost(userDetails, postId, postRequestDto);
        return ResponseEntity.ok(PostDto.Response.of(post));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto.Response> get(@PathVariable Long postId) {
        Post findPost = postService.findBoard(postId);

        return ResponseEntity.ok(PostDto.Response.of(findPost));
    }

    @GetMapping
    public ResponseEntity<List<PostDto.Response>> getAll() {
        List<Post> postList = postService.findAllBoards();

        return ResponseEntity.ok(postService.toResponseDtos(postList));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal UserDetails userDetails,
                                    @PathVariable Long postId) {
        postService.deletePost(userDetails, postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/{postTitle}")
    public ResponseEntity<List<PostDto.Response>> search(@PathVariable String postTitle) {
        List<Post> postList = postService.searchPost(postTitle);

        return ResponseEntity.ok(postService.toResponseDtos(postList));
    }

    @PostMapping("/{postId}/likes")
    public ResponseEntity<?> likeUp(@PathVariable Long postId) {
        postService.likeUp(postId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}/likes")
    public ResponseEntity<?> likeDown(@PathVariable Long postId) {
        postService.likeDown(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{category-name}")
    public ResponseEntity<List<PostDto.Response>> getByCategory(@PathVariable("category-name") String categoryName) {
        List<Post> postList = postService.getByCategory(categoryName);

        return ResponseEntity.ok(postService.toResponseDtos(postList));
    }

    @GetMapping("/top5")
    public ResponseEntity getTop5() {
        List<Post> postList = postService.getTop5();
        return ResponseEntity.ok(postService.toResponseDtos(postList));
    }
}
