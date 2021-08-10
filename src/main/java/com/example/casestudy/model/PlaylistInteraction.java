package com.example.casestudy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class PlaylistInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long senderId;
    private Long recieverId;
    private Long playlistId;
    private String comment;
    private Date createdAt;
    private String link;
    private boolean likes = false;
    private boolean isRead = false;

    public PlaylistInteraction(){
        this.createdAt = new Date();
    }
}
