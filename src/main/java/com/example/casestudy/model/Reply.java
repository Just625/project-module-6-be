package com.example.casestudy.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createdAt;
    @ManyToOne
    private User user;
    private String content;
    @ManyToOne
    private Comment comment;
}
