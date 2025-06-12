package com.spring.newspid.dto.user;

import com.spring.newspid.domain.User;

import java.time.LocalDateTime;

public class UserCheckResponseDto {
    // 속성
    private Long id;
    private String userEmail;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public UserCheckResponseDto(User user) {
        this.id = getId();
        this.userEmail = getUserEmail();
        this.content = getContent();
        this.userName = getUserName();
        this.createdAt = getCreatedAt();
        this.updatedAt = getUpdatedAt();
    }

    // 기능
    // 게터
    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}