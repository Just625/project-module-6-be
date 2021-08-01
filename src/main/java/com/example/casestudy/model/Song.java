package com.example.casestudy.model;

import lombok.Data;

import javax.persistence.*;
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
    @ManyToOne
    private  User user;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Singer> singers;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Comment> commentList;
}
