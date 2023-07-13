package com.ohgiraffers.goonthatbackend.metamate.member.entity;

import com.ohgiraffers.goonthatbackend.metamate.member.domain.Role;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "meta_user")
@Entity
public class MetaUser implements UserDetails {

    /**
     * UserDetail이란 Spring Security에서 사용자의 정보를 담는 인터페이스.
     * 인증에 성공하여 생성된 UserDetails 객체는 Authentication 객체를 구현한
     * UsernamePasswordAuthenticationToken을 생성하기 위해 사용됨.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    @Length(min = 4, max = 100)
    private String password;

    @Column
    private String name;

    @Column(unique = true)
    private String nickname;

    @Column
    private String number;

    @Column
    private String major;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public MetaUser(String email, String password, String name, String nickname, String number, String major, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.major = major;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getValue()));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
