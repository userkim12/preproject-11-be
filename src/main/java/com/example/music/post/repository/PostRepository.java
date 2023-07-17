package com.example.music.post.repository;

import com.example.music.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByTitleLikeOrderByCreatedAtDesc(String title);
    List<Post> findAllByCategoryOrderByCreatedAtDesc(String category);
    List<Post> findTop5ByOrderByLikesDesc();
}
