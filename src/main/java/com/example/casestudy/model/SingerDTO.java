package com.example.casestudy.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SingerDTO {
    private String name;
    private String gender;
    private Long genres;
    private String dOB;
    private String biography;
    private String band;
    private String additionalInfo;
    private String imageUrl;
}
