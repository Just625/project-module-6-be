package com.example.casestudy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres;
    private String dOB;
    private String biography;
    private String band;
    private String popularSong;
    private int likes;
    private String additionalInfo;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Comment> commentList;
}
