package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.service;

import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.service.FreeBoardPostService;
import com.ohgiraffers.goonthatbackend.metamate.freeboard.command.domain.repository.FreeBoardPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class FreeBoardPostImpsService implements FreeBoardPostService {

    private final FreeBoardPostRepository freeBoardRepository;

    @Autowired
    public FreeBoardPostService(FreeBoardPostRepository freeBoardRepository) {
        this.freeBoardRepository = freeBoardRepository;
    }


}
