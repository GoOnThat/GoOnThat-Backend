package com.ohgiraffers.goonthatbackend.metamate.message;

import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUser;
import com.ohgiraffers.goonthatbackend.metamate.domain.user.MetaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final MetaUserRepository metaUserRepository;

    @Transactional
    public MessageDto write(MessageDto messageDto) {
        MetaUser receiver = metaUserRepository.findByNickname(messageDto.getReceiverNickname());
        MetaUser sender = metaUserRepository.findByNickname(messageDto.getSenderNickname());

        Message message = new Message();
        message.setReceiver(receiver);
        message.setSender(sender);

        message.setTitle(messageDto.getTitle());
        message.setContent(messageDto.getContent());
        message.setDeletedByReceiver(false);
        message.setDeletedBySender(false);
        messageRepository.save(message);

        return MessageDto.toDto(message);
    }


    @Transactional(readOnly = true)
    public List<MessageDto> receivedMessage(MetaUser user) {
        List<Message> messages = messageRepository.findAllByReceiver(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message 에서 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내줌
            if(!message.isDeletedByReceiver()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }

    // 받은 편지 삭제
    @Transactional
    public Object deleteMessageByReceiver(Long id, MetaUser user) {
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("메시지를 찾을 수 없습니다.");
        });

        if(user == message.getSender()) {
            message.deleteByReceiver(); // 받은 사람에게 메시지 삭제
            if (message.isDeleted()) {
                // 받은사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제요청
                messageRepository.delete(message);
                return "양쪽 모두 삭제";
            }
            return "한쪽만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
    }



    @Transactional(readOnly = true)
    public List<MessageDto> sentMessage(MetaUser user) {
        List<Message> messages = messageRepository.findAllBySender(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message 에서 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내줌
            if(!message.isDeletedBySender()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }


    // 보낸 편지 삭제
    @Transactional
    public Object deleteMessageBySender(Long id, MetaUser user) {
        Message message = messageRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("메시지를 찾을 수 없습니다.");
        });

        if(user == message.getSender()) {
            message.deleteBySender(); // 받은 사람에게 메시지 삭제
            if (message.isDeleted()) {
                // 받은사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제요청
                messageRepository.delete(message);
                return "양쪽 모두 삭제";
            }
            return "한쪽만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }


    }
}