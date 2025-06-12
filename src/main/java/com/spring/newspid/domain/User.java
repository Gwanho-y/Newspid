package com.spring.newspid.domain;

import com.spring.newspid.dto.user.UserCreateRequestDto;
import com.spring.newspid.dto.user.UserEditRequestDto;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "users")
public class User {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100, name = "user_email")
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    //현재 UTC 시간으로 초기화

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.createdAt = now;
        this.updatedAt = now;
    }

    //현재 UTC 시간으로 초기화

    @PreUpdate
    public void onUpdate() {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        this.updatedAt = now;
    }

    //생성자
    public User() {
    }

    public User(String userEmail, String password, String userName, String content) {
        this.userEmail = userEmail;
        this.password = password;
        this.userName = userName;
        this.content = content;
    }

    // 기능
    public void updateUser(UserEditRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.content = requestDto.getContent();
    }

    // 게터
    public Long getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;

    }
}