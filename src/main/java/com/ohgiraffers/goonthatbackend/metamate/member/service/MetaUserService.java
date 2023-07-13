package com.ohgiraffers.goonthatbackend.metamate.member.service;

import com.ohgiraffers.goonthatbackend.metamate.member.domain.Role;
import com.ohgiraffers.goonthatbackend.metamate.member.dto.MetaUserDto;
import com.ohgiraffers.goonthatbackend.metamate.member.dto.MetaUserJoinDto;
import com.ohgiraffers.goonthatbackend.metamate.member.entity.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.member.repository.MetaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional // 로직을 처리하다가 에러가 발생하면, 변경된 데이터를 로직을 수행하기 이전 상태로 롤백
public class MetaUserService {

    private final MetaUserRepository metaUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MetaUserDto createUser(MetaUserJoinDto metaUserJoinDto) {

        /**
         * createUser 함수에서는 인자로 받은 MetaUserJoinDto의 이메일이
         * DB에 존재하지 않을 경우 해당 유저 정보를 DB에 저장
         * DB에 저장할 때는 BCryptPasswordEncoder를 사용하여 암호화를 시키고 저장
         * 만약 DB에 중복된 이메일이 존재할 경우 null 반환
         */

        // 이메일 중복 확인
        if(metaUserRepository.findByEmail(metaUserJoinDto.getEmail()) != null){
            return null;
        }

        // 가입에 성공한 모든 유저는 "ASSOCIATE" 권한 부여, 개발단계에서는 "USER" 부여
        MetaUser metaUser = metaUserRepository.save(MetaUser.builder()
                .password(bCryptPasswordEncoder.encode(metaUserJoinDto.getPassword()))
                .email(metaUserJoinDto.getEmail())
                .name(metaUserJoinDto.getName())
                .nickname(metaUserJoinDto.getNickname())
                .number(metaUserJoinDto.getNumber())
                .major(metaUserJoinDto.getMajor())
                .role(Role.USER)
                .build());

        return MetaUserDto.builder()
                .id(metaUser.getId())
                .email(metaUser.getEmail())
                .password(metaUser.getPassword())
                .name(metaUser.getName())
                .nickname(metaUser.getNickname())
                .number(metaUser.getNumber())
                .major(metaUser.getMajor())
                .role(metaUser.getRole())
                .build();
    }
}
