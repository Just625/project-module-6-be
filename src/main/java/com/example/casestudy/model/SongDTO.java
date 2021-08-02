package com.example.casestudy.model;

import lombok.Data;

import java.util.Set;

@Data
public class SongDTO {
    private String name;
    private String author;
    private Long genres;
    private Long singers;
}
