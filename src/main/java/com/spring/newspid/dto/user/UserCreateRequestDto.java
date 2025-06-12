package com.spring.newspid.dto.user;

public class UserCreateRequestDto {
    // 속성
    private String userEmail;
    private String password;
    private String userName;
    private String content;

    // 생성자
    public UserCreateRequestDto() {
    }

    // 기능
    // 게터
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
}