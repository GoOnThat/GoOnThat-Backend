package com.ohgiraffers.goonthatbackend.metamate.message;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/messages")
@RequiredArgsConstructor
@Controller
public class MessageController {

    private final MessageService messageService;
    private final MetaUserRepository metaUserRepository;

    @GetMapping("/received")
    public String getReceivedMessage(
            @LoginUser SessionMetaUser loginUser,
            Model model) {
        MetaUser metaUser = metaUserRepository.findById(loginUser.getId()).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        model.addAttribute("receivedMessage", messageService.receivedMessage(metaUser));

        return "messages/received";
    }

    @GetMapping("/sendForm")
    public String sendMessageForm(
            @LoginUser SessionMetaUser loginUser,
            @ModelAttribute("messageDto") MessageDto messageDto,
            Model model) {

        if (loginUser == null) {
            return "redirect:/messages/sendForm";
        }
        model.addAttribute("sessionUser", loginUser);

        return "messages/sendForm";
    }

    @PostMapping("/sendForm")
    public String sendMessage(
            @LoginUser SessionMetaUser loginUser,
            @ModelAttribute("messageDto") MessageDto messageDto) {
        MetaUser user = metaUserRepository.findById(loginUser.getId()).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });
        messageDto.setSenderNickname(user.getNickname());
        messageService.write(messageDto);

        return "redirect:/messages/sendForm";
    }


    @GetMapping("/sent")
    public String getSentMessage(@LoginUser SessionMetaUser loginUser,
                                 Model model) {
        MetaUser user = metaUserRepository.findById(loginUser.getId()).orElseThrow(() -> {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        });

        model.addAttribute("sentMessage", messageService.sentMessage(user));

        return "messages/sent";
    }

}