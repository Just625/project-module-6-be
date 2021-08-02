package com.example.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private String name;
    private String description;
    private Set<Genre> genres;
    private User user;
    private String imgUrl;

}
