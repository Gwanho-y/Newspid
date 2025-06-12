package com.spring.newspid.controller;

import com.spring.newspid.domain.User;

import com.spring.newspid.dto.regi.RegiRequestDto;
import com.spring.newspid.dto.regi.UserRegiDto;
import com.spring.newspid.dto.user.UserCreateRequestDto;
import com.spring.newspid.dto.user.UserCreateResponseDto;
import com.spring.newspid.service.RegiService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RestController
@RequestMapping("/regi")
public class RegiController {
    //속성
    private final RegiService regiService;
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(
            "xA7bS8p3Wj6nKdL0gZf2QeX9UvHcRyTmBpNcLrVoYiEjTsMwZqFtGhAkDsPlMzXcN"
                    .getBytes(StandardCharsets.UTF_8));

    //생성자
    public RegiController(RegiService regiService) {
        this.regiService = regiService;
    }

    //기능

    //회원가입

    @PostMapping
    public ResponseEntity<UserCreateResponseDto> createUserAPI(@RequestBody UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = regiService.createUserService(requestDto);
        ResponseEntity<UserCreateResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    //로그인

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegiRequestDto dto) {
        try {
            // 유효성 검증 + 사용자 조회
            User user = regiService.regi(dto);


            //로그인 성공 시
            UserRegiDto.LoginState.isLoggedIn = 1;

            // JWT 생성
            String token = Jwts.builder()
                    .setSubject(user.getUserEmail())
                    .setIssuedAt(new Date())
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                    .compact();

            // 응답 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body("로그인 성공");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("로그인 실패: " + e.getMessage());
        }
    }
}