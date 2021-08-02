package com.example.casestudy.controller;

import com.example.casestudy.model.Genre;
import com.example.casestudy.service.genre.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class GenreController {
    @Autowired
    private IGenreService genreService;

    @GetMapping("genres")
    public ResponseEntity<Iterable<Genre>> getAllGenre (){
        return new ResponseEntity<>(genreService.findAll(), HttpStatus.OK);
    }
}
