package com.spring.newspid.service;

import com.spring.newspid.domain.Post;
import com.spring.newspid.dto.post.*;
import com.spring.newspid.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    // 속성
    private PostRepository postRepository;

    // 생성자
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 기능

    /**
     * 게시물 생성 기능
     */
    @Transactional
    public PostCreateResponseDto createPostService(PostCreateRequestDto requestDto) {
        Post foundPost = new Post(requestDto);
        Post savePost = postRepository.save(foundPost);
        PostCreateResponseDto responseDto = new PostCreateResponseDto(savePost);
        return responseDto;
    }

    /**
     * 게시물 전체 조회 기능
     */
    public PostListResponseDto getPostListService(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Post> postPage = postRepository.findAll(pageable);

        List<PostListDto> postListDtoList = postPage.getContent().stream()
                .map(PostListDto::new)
                .collect(Collectors.toList());

        PostListResponseDto responseDto = new PostListResponseDto(postListDtoList);
        return responseDto;
    }

    /**
     * 게시물 단건 조회 기능
     */
    public PostDetailResponseDto getPostDetailService(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post fountPost = optionalPost.get();
            PostDetailResponseDto responseDto = new PostDetailResponseDto(fountPost);
            return responseDto;
        } else {
            return null;
        }
    }

    /**
     * 게시물 수정 기능
     */
    @Transactional
    public PostUpdateResponseDto updatePostService(Long postid, PostUpdateRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(postid);
        if (optionalPost.isPresent()) {
            Post foundPost = optionalPost.get();
            foundPost.updatePost(requestDto);
            PostUpdateResponseDto responseDto = new PostUpdateResponseDto(foundPost);
            return responseDto;
        } else {
            return null;
        }
    }

    /**
     * 게시물 삭제 기능
     */
    public PostDeleteResponseDto deletePostService(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post foundPost = optionalPost.get();
            postRepository.delete(foundPost);
            PostDeleteResponseDto responseDto = new PostDeleteResponseDto(200);
            return responseDto;
        } else {
            PostDeleteResponseDto responseDto = new PostDeleteResponseDto(400, "존재하지 않는 게시물입니다.");
            return responseDto;
        }
    }
}