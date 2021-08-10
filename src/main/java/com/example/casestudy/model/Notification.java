package com.example.casestudy.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User sender;
    private Long recieverId;
    private String content;
    private String link;
    private boolean status;
    private Date createDate;
    private String action;
}
