package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.vo;

import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;

@Embeddable
@Getter
@EqualsAndHashCode
public class BoardWriter {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private MetaUser metaUser;

    protected BoardWriter() {

    }
    public BoardWriter(MetaUser metaUser) {
        validateMemberName(metaUser);
        this.metaUser = metaUser;
    }

    private void validateMemberName(MetaUser metaUser) {

        if(metaUser.getId() == null) {
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }else if(metaUser.getId().equals("")){
            throw new IllegalArgumentException("회원번호가 공백입니다.");
        }
    }

}

