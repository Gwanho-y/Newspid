package com.spring.newspid.dto.user;

public class UserEditRequestDto {
    //속성
    private String userEmail;
    private String password;
    private String userName;
    private String content;

    //생성자
    //기능
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