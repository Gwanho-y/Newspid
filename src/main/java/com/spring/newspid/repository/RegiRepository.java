package com.spring.newspid.repository;

import com.spring.newspid.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegiRepository extends JpaRepository<User, Long> {
    //기능
    //이메일 기준으로 사용자 조회
    Optional<User> findByUserEmail(String userEmail);
    //회원가입시 사용할 기능
    boolean existsByUserEmail(String userEmail);

}
