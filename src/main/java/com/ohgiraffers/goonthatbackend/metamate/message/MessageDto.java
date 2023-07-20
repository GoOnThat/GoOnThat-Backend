package com.ohgiraffers.goonthatbackend.metamate.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private String title;
    private String content;
    private String senderNickname;
    private String receiverNickname;

    public static MessageDto toDto(Message message) {
        return new MessageDto(
                message.getTitle(),
                message.getContent(),
                message.getSender().getNickname(),
                message.getReceiver().getNickname()
        );
    }
}
