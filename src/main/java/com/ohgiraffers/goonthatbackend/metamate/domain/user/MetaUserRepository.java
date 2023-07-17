package com.ohgiraffers.goonthatbackend.metamate.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetaUserRepository extends JpaRepository<MetaUser, Long> {

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<MetaUser> findByEmail(String email);
    Optional<MetaUser> findById(Long id);
    void deleteById(Long id);
}
