package com.spring.newspid.dto.post;

import java.util.List;

public class PostListResponseDto {
    // 속성
    private List<PostListDto> postListDto;

    // 생성자
    public PostListResponseDto(List<PostListDto> postListDto) {
        this.postListDto = postListDto;
    }

    // 기능
    public List<PostListDto> getPostListDto() {
        return postListDto;
    }
}
