package com.example.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
public class SongInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long receiverId;
    private Long songId;
    private String comment = null;
    private Date createdAt;
    private String link;
    private boolean likes = false;
    private boolean isRead = false;

    public SongInteraction() {
        this.createdAt = new Date();
    }

    public SongInteraction(Long senderId, Long recieverId, Long songId, String comment, Date createdAt, String link, boolean isRead) {
        this.senderId = senderId;
        this.receiverId = recieverId;
        this.songId = songId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.link = link;
        this.isRead = isRead;
    }
}
