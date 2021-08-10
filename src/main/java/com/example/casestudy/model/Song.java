package com.example.casestudy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String author;
    private String fileMp3;
    private String imgUrl;
    private int listenCount;
    private int likes;
    private String album;
    @ManyToOne
    private  User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres = new HashSet<>();
    @ManyToMany
    private Set<Singer> singers = new HashSet<>();
    private Date createdAt;

    public Song() {
        this.createdAt = new Date();
    }
}
