package com.example.casestudy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SongInteractionDTO {
    private Long senderId;
    private Long receiverId;
    private Long songId;
    private String comment = null;
    private String link = null;
    private boolean likes = false;
    private boolean isRead = false;
}
