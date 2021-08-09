package com.example.casestudy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;
    private String description;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date createdAt;
    @ManyToOne
    private User user;
    private Date lastUpdated;
    private int listenCount;
    private int likes;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Genre> genres;
    @ManyToMany
    private Set<Song> songs;
    private String imgUrl;

    public Playlist (){
        this.createdAt = new Date();
    }
}
