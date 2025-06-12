package com.spring.newspid.service;

import com.spring.newspid.config.PasswordEncoder;
import com.spring.newspid.dto.regi.RegiRequestDto;
import com.spring.newspid.domain.User;
import com.spring.newspid.dto.user.UserCreateRequestDto;
import com.spring.newspid.dto.user.UserCreateResponseDto;
import com.spring.newspid.repository.RegiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegiService {
    //속성
    private final RegiRepository regiRepository;
    private final PasswordEncoder passwordEncoder;

    //생성자
    public RegiService(RegiRepository regiRepository, PasswordEncoder passwordEncoder) {
        this.regiRepository = regiRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //기능

    /**
     * 회원 가입 기능
     */
    @Transactional
    public UserCreateResponseDto createUserService(UserCreateRequestDto requestDto) {
        //매일 중복 확인
        if (regiRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        // User 객체 생성 시 암호화된 비밀번호로 세팅
        User user = new User(
                requestDto.getUserEmail(),
                encodedPassword,
                requestDto.getUserName(),
                requestDto.getContent()
        );
        User saveUser = regiRepository.save(user);
        return new UserCreateResponseDto(saveUser);
    }

    /**
     * 로그인 기능
     */
    @Transactional
    public User regi(RegiRequestDto requestDto) {
        //데이터 준비
        String userEmail = requestDto.getUserEmail();
        String password = requestDto.getPassword();
        //이메일로 사용자 찾기
        User user = regiRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        //비밀번호 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        //로그인 성공
        return user;
    }
}
