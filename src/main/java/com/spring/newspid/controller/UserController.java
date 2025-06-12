package com.spring.newspid.controller;

import com.spring.newspid.dto.user.UserCheckResponseDto;
import com.spring.newspid.dto.user.UserEditRequestDto;
import com.spring.newspid.dto.user.UserEditResponseDto;
import com.spring.newspid.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    // 속성
    private final UserService userService;

    // 생성자
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 기능

    //유저 프로필 조회

    @GetMapping("/{userId}")
    public ResponseEntity<UserEditResponseDto> getUserAPI(@PathVariable("userId") Long userId) {
        UserEditResponseDto responseDto = userService.getUserService(userId);
        ResponseEntity<UserEditResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    //유저 정보 수정

    @PatchMapping("/{userId}")
    public ResponseEntity<UserEditResponseDto> updateUserAPI(@PathVariable("userId") Long userId , @RequestBody UserEditRequestDto requestDto) {
        UserEditResponseDto responseDto = userService.updateUserService(userId, requestDto);
        ResponseEntity<UserEditResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
}