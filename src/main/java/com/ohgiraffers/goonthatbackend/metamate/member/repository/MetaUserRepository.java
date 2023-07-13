package com.ohgiraffers.goonthatbackend.metamate.member.repository;

import com.ohgiraffers.goonthatbackend.metamate.member.entity.MetaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaUserRepository extends JpaRepository<MetaUser, Long> {
    /**
     * 회원가입 시 중복된 회원이 있는지 검사하기 위해 이메일로 회원을 검사하는 메소드 작성
     */
    MetaUser findByEmail(String email);
}
