package com.example.casestudy.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
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

    public SingerInteraction(){
        this.createdAt = new Date();
    }

}
