package com.example.casestudy.model;

import lombok.Data;

@Data
public class SongDTO {
    private String name;
    private String author;
    private Long genres;
    private Long singers;
    private String mp3Url;
    private String description;
    private String album;
    private String imgUrl;
    private Long userId;
}
