package com.spring.newspid.controller;

import com.spring.newspid.dto.post.*;
import com.spring.newspid.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    // 속성
    private PostService postService;

    // 생성자
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 기능

    //게시물 생성

    @PostMapping
    public ResponseEntity<PostCreateResponseDto> createPostAPI(@RequestBody PostCreateRequestDto requestDto) {
        PostCreateResponseDto responseDto = postService.createPostService(requestDto);
        ResponseEntity<PostCreateResponseDto> response = new ResponseEntity<>(responseDto , HttpStatus.OK);
        return response;
    }

    //게시물 전체 조회

    @GetMapping
    public ResponseEntity<PostListResponseDto> getPostListAPI(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PostListResponseDto responseDto = postService.getPostListService(page, size);
        ResponseEntity<PostListResponseDto> response = new ResponseEntity<>(responseDto , HttpStatus.OK);
        return response;
    }

    //게시물 단건 조회

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponseDto> getPostDetailAPI(@PathVariable("postId") Long postId) {
        PostDetailResponseDto responseDto = postService.getPostDetailService(postId);
        ResponseEntity<PostDetailResponseDto> response = new ResponseEntity<>(responseDto , HttpStatus.OK);
        return response;
    }

    //게시물 수정

    @PatchMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> updatePostAPI(@PathVariable("postId") Long postId , @RequestBody PostUpdateRequestDto requestDto) {
        PostUpdateResponseDto responseDto = postService.updatePostService(postId, requestDto);
        ResponseEntity<PostUpdateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    //게시물 삭제

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> deletePostAPI(@PathVariable("postId") Long postId) {
        PostDeleteResponseDto responseDto = postService.deletePostService(postId);
        ResponseEntity<PostDeleteResponseDto> response = new ResponseEntity<>(responseDto , HttpStatus.OK);
        return response;
    }
}