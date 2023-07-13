package com.ohgiraffers.goonthatbackend.metamate.member.service;

import com.ohgiraffers.goonthatbackend.metamate.member.repository.MetaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetaUserDetailService implements UserDetailsService {

    /**
     * UserDetailService는 Spring Security에서 유저의 정보를 가져오는 인터페이스
     * Spring Security에서 유저의 정보를 불러오기 위해 구현해야 하는 인터페이스로
     * 기본 오버라이드 메서드는 loadUserByUsername이 있음.
     *
     * loadUserByUsername 메서드는 반환 타입이 UserDetails이고
     * 유저의 정보를 불러와서 UserDetails로 반환해 줌
     * (UserRepository를 주입받아 DB와 연결하여 처리)
     */

    private final MetaUserRepository metaUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional
                .ofNullable(metaUserRepository.findByEmail(email))
                .orElseThrow(() -> new BadCredentialsException("이메일 주소를 확인해주세요."));
    }
}
