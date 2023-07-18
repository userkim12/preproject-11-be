package com.example.music.post.service;

import com.example.music.post.dto.PostDto;
import com.example.music.post.entity.CategoryEnum;
import com.example.music.post.entity.Post;
import com.example.music.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class PostService {
    private static final int UP = 1;
    private static final int DOWN = -1;
    private final PostRepository postRepository;

    public Post createPost(PostDto.Request postRequestDto) {
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

        return postRepository.findAllByOrderByCreatedAtDesc();
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
        return postRepository.findByTitleLikeOrderByCreatedAtDesc("%" + postTitle + "%");
    }

    public List<Post> getByCategory(String categoryName) {
        System.out.println("++++++++++++++++++"+categoryName+"++++++++++++++++++");
        CategoryEnum getCategory = CategoryEnum.nameOf(categoryName);
        return postRepository.findAllByCategoryOrderByCreatedAtDesc(getCategory);
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

    public List<PostDto.Response> toResponseDtos(List<Post> postList) {

        return postList.stream().map(PostDto.Response::nonCommentOf).toList();
    }

    @Scheduled(cron = "0 0 1 * * *") //1분마다 실행, Top 5 게시물 조회시에 일정한 시간마다 갱신하기 ?!? -> 주기 : 매일 새벽 1시
    public List<Post> getTop5() {
        return postRepository.findTop5ByOrderByLikesDesc();
    }
}
