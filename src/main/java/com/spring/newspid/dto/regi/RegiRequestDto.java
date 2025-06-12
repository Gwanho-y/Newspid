package com.spring.newspid.dto.regi;

public class RegiRequestDto {
    //속성
    private String userEmail;
    private String password;

    //생성자
    //기본생성자
    public RegiRequestDto() {
    }

    //loginService 쓰인다.
    public RegiRequestDto(String userEmail, String password) {
        this.userEmail = userEmail;
        this.password = password;
    }
    //기능

    public String getUserEmail() {
        return userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
