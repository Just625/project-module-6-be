package com.example.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    @NotEmpty
    private String name;
    private String description;
    private Genre genres;
    private User user;
    private String imgUrl;

}
