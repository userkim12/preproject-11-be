package com.example.music.post.service;

import com.example.music.post.dto.PostRequestDto;
import com.example.music.post.entity.Post;
import com.example.music.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private static final int UP = 1;
    private static final int DOWN = -1;
    private final PostRepository postRepository;

    public Post createPost(PostRequestDto postRequestDto) {
        Post post = postRequestDto.toPostEntity();

        return postRepository.save(post);
    }

//    @Transactional
//    public Post updatePost(Long postId, PostRequestDto postRequestDto) {
//        Post originalPost = verifyPostExists(postId);
//        String author = originalPost.getUser().getUsername();
//
//        String currentUser = userDetails.getUsername();
//
//
//        if(isAdminOrAuthor(author, currentUser)) {
//            // postRequestDto를 입력과 수정에 같이 사용해버리면
//            // 입력 때에는 모든 필드값이 있어야 하는데 수정은 하나만 있어도 됨
//            // dto에서 notnull 유효성 검사는 안해야할듯
//            originalPost.update(postRequestDto.toPostEntity());
//        }
//
//        return originalPost;
//    }

    public Post findBoard(Long postId) {

        return verifyPostExists(postId);
    }

    public List<Post> findAllBoards() {

        return postRepository.findAll();
    }

//    public void deletePost(Long postId) {
//        Post post = verifyPostExists(postId);
//        String author = post.getUser().getUsername();
//        String currentUser = userDetails.getUsername();
//
//        if(isAdminOrAuthor(author, currentUser)) {
//            postRepository.delete(post);
//        }
//    }

    public List<Post> searchPost(String postTitle) {
        return postRepository.findAllByTitle(postTitle);
    }

    public void likeUp(Long postId) {
        Post post = verifyPostExists(postId);
        post.changLikes(UP);
    }

    public void likeDown(Long postId) {
        Post post = verifyPostExists(postId);
        post.changLikes(DOWN);
    }

    private Post verifyPostExists(Long postId) {
        return postRepository.findByPostId(postId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 게시글입니다.")
        );
    }

    private boolean isAdminOrAuthor(String author, String currentUser) {
        if(!author.equals(currentUser) || currentUser.equals("ADMIN")) {
            throw new RuntimeException("작성자나 관리자만 삭제/수정이 가능합니다.");
        }

        return true;
    }
}
