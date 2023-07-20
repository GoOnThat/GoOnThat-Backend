package com.ohgiraffers.goonthatbackend.metamate.freeboard.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FreeBoardEditDTO {

    private String boardCategory;
    private String boardTitle;
    private String boardContent;


}
