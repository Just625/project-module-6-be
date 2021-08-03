package com.example.casestudy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    @ManyToOne
    private User user;
    private String lastUpdated;
    private int listenCount;
    private int likes;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Song> songs;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Comment> commentList;
}
