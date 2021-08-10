package com.example.casestudy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SingerInteractionDTO {
    private Long senderId;
    private Long recieverId;
    private Long singerId;
    private String comment;
    private String link;
    private boolean likes = false;
    private boolean isRead = false;
}
