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
@NoArgsConstructor
public class SingerInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long recieverId;
    private Long singerId;
    private String comment = null;
    private Date createdAt;
    private String link;
    private boolean likes = false;
    private boolean isRead = false;

    public SingerInteraction(Long senderId, Long recieverId, Long singerId, String comment, Date createdAt, String link, boolean isRead) {
        this.senderId = senderId;
        this.recieverId = recieverId;
        this.singerId = singerId;
        this.comment = comment;
        this.createdAt = createdAt;
        this.link = link;
        this.isRead = isRead;
    }
}
