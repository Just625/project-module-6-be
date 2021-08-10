package com.example.casestudy.controller;

import com.example.casestudy.model.*;
import com.example.casestudy.service.genre.IGenreService;
import com.example.casestudy.service.singer.ISingerService;
import com.example.casestudy.service.song.ISongService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/singers")
@CrossOrigin("*")
public class SingerController {
    @Autowired
    private ISingerService singerService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private ISongService songService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Singer> getSinger(@PathVariable Long id) {
        Optional<Singer> singerOptional = singerService.findById(id);
        if (singerOptional.isPresent()) {
            return new ResponseEntity<>(singerOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Singer> save(@RequestBody SingerDTO singerDTO) {
        Singer singer = new Singer();
        singer.setName(singerDTO.getName());
        singer.setDateOfBirth(singerDTO.getDateOfBirth());
        singer.setGender(singerDTO.getGender());
        singer.setBand(singerDTO.getBand());
        singer.setBiography(singerDTO.getBiography());
        singer.setAdditionalInfo(singerDTO.getAdditionalInfo());
        Optional<User> userOptional = userService.findById(singerDTO.getUser());
        userOptional.ifPresent(singer::setUser);
        Optional<Genre> genreOptional = genreService.findById(singerDTO.getGenres());
        genreOptional.ifPresent(genre -> singer.getGenres().add(genre));
        singer.setImageUrl(singerDTO.getImageUrl());

        return new ResponseEntity<>(singerService.save(singer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Singer> update(@PathVariable Long id, @RequestBody Singer singer) {
        Optional<Singer> singerOptional = singerService.findById(id);
        if (singerOptional.isPresent()) {
            return new ResponseEntity<>(singerService.save(singer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Long userId) {
        Optional<Singer> singerOptional = singerService.findById(id);
        if (singerOptional.isPresent() && singerOptional.get().getUser().getId() == userId) {
            singerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}/songs")
    public ResponseEntity<?> getAllSongsOfSinger (@PathVariable Long id){
        List<Song> songs = songService.findSongsBySinger(id);
        return new ResponseEntity<>(songs,HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        Optional<Singer> singerOptional = singerService.findSingerByName(name);
        if (singerOptional.isPresent()){
            return new ResponseEntity<>(singerOptional.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Singer(),HttpStatus.NO_CONTENT);

        }
    }
}
