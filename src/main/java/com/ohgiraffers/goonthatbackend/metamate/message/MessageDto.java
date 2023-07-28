package com.ohgiraffers.goonthatbackend.metamate.message;

import com.ohgiraffers.goonthatbackend.metamate.common.CalcCreateDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private Long id;
    private String title;
    private String content;
    private String senderNickname;
    private String receiverNickname;
    private String createdAt;

    public static MessageDto toDto(Message message) {
        CalcCreateDate calcCreateDate = new CalcCreateDate();
        return new MessageDto(
                message.getId(),
                message.getTitle(),
                message.getContent(),
                message.getSender().getNickname(),
                message.getReceiver().getNickname(),
                calcCreateDate.calcCreateDate(message.getCreatedAt())
        );
    }
}
