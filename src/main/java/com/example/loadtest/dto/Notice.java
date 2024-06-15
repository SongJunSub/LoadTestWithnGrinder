package com.example.loadtest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice {

    private Long id;

    private String title;

    private String content;

    private String who;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

}