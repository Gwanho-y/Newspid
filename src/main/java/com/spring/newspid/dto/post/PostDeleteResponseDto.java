package com.spring.newspid.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PostDeleteResponseDto {
    // 속성
    private int status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    // 생성자
    public PostDeleteResponseDto(int status) {
        this.status = status;
    }

    public PostDeleteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 기능
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
