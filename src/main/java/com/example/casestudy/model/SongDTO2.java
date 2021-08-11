package com.example.casestudy.model;

import lombok.Data;

import java.util.List;

@Data
public class SongDTO2 {
    private Long id;
    private String name;
    private String author;
    private Long genres;
    private List<String> singers;
    private String mp3Url;
    private String description;
    private String album;
    private String imgUrl;
    private Long userId;
}
