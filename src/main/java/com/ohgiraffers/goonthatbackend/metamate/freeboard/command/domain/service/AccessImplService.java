package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.AccessService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.aggregate.entity.FreeBoardPost;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import org.springframework.stereotype.Service;

@Service
public class AccessImplService implements AccessService {

    public void validateUserAccess(FreeBoardPost boardPost, SessionMetaUser user) {
        // 게시물의 작성자와 로그인한 사용자를 비교하여 권한 검증
        if (!boardPost.getMetaUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.USER_BAD_REQUEST);
        }
    }
}
