package com.spring.newspid.service;

import com.spring.newspid.config.PasswordEncoder;
import com.spring.newspid.domain.User;
import com.spring.newspid.dto.user.*;
import com.spring.newspid.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {
    // 속성
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 생성자
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 기능
    /**
     * 유저 조회 기능
     */
    public UserEditResponseDto getUserService(Long userID) {
        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()) {
            User founduser = optionalUser.get();
            UserEditResponseDto responseDto = new UserEditResponseDto(founduser);
            return responseDto;
        } else {
            return null;
        }
    }

    /**
     * 유저 정보 업데이트 기능
     */
    @Transactional
    public UserEditResponseDto updateUserService(Long userId, UserEditRequestDto requestDto){
        String password = requestDto.getPassword();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User founduser = optionalUser.get();
            if (founduser.getUserEmail().equals(requestDto.getUserEmail()) && passwordEncoder.matches(password, founduser.getPassword())) {
                founduser.updateUser(requestDto);

                UserEditResponseDto responseDto = new UserEditResponseDto(founduser);
                return responseDto;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

//    /**
//     * 회원 가입 기능
//     */
//    @Transactional
//    public UserCreateResponseDto UserCreateService(UserCreateRequestDto requestDto) {
//        //매일 중복 확인
//        if (UserRepository.existsByUserEmail(requestDto.getUserEmail())) {
//            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
//        }
//        // 비밀번호 암호화
//        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
//
//        // User 객체 생성 시 암호화된 비밀번호로 세팅
//        User user = new User(
//                requestDto.getUserEmail(),
//                encodedPassword,
//                requestDto.getUserName(),
//                requestDto.getContent()
//        );
//        User saveUser = UserRepository.save(user);
//        return new UserCreateResponseDto(saveUser);
//    }

}