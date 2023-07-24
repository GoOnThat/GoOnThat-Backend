package com.ohgiraffers.goonthatbackend.metamate.message;

import com.ohgiraffers.goonthatbackend.metamate.auth.LoginUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import com.ohgiraffers.goonthatbackend.metamate.exception.CustomException;
import com.ohgiraffers.goonthatbackend.metamate.exception.ErrorCode;
import com.ohgiraffers.goonthatbackend.metamate.web.dto.user.SessionMetaUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("/messages")
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

        return "/messages/sendForm";
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


//    @ApiOperation(value = "받은 쪽지 삭제하기", notes = "받은 쪽지를 삭제합니다.")
//    @ResponseStatus(HttpStatus.OK)
//    @DeleteMapping("/messages/received/{id}")
//    public Response<?> deleteReceivedMessage(@PathVariable("id") Integer id) {
//        // 임의로 유저 정보를 넣었지만, JWT 도입하고 현재 로그인 된 유저의 정보를 넘겨줘야함
//        User user = userRepository.findById(1).orElseThrow(() -> {
//            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
//        });
//
//        return new Response<>("삭제 성공", "받은 쪽지인, " + id + "번 쪽지를 삭제했습니다.", messageService.deleteMessageByReceiver(id, user));
//    }
//
//    @ApiOperation(value = "보낸 쪽지 삭제하기", notes = "보낸 쪽지를 삭제합니다.")
//    @ResponseStatus(HttpStatus.OK)
//    @DeleteMapping("/messages/sent/{id}")
//    public Response<?> deleteSentMessage(@PathVariable("id") Integer id) {
//        // 임의로 유저 정보를 넣었지만, JWT 도입하고 현재 로그인 된 유저의 정보를 넘겨줘야함
//        User user = userRepository.findById(1).orElseThrow(() -> {
//            return new IllegalArgumentException("유저를 찾을 수 없습니다.");
//        });
//
//        return new Response<>("삭제 성공", "보낸 쪽지인, " + id + "번 쪽지를 삭제했습니다.", messageService.deleteMessageBySender(id, user));
//    }


}