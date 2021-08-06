package com.example.casestudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;
    private String description;
    private Genre genres;
    private User user;
    private String imgUrl;

}
