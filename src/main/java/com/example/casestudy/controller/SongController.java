package com.example.casestudy.controller;

import com.example.casestudy.model.Genre;
import com.example.casestudy.model.Singer;
import com.example.casestudy.model.Song;
import com.example.casestudy.model.SongDTO;
import com.example.casestudy.service.genre.IGenreService;
import com.example.casestudy.service.singer.ISingerService;
import com.example.casestudy.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class SongController {
    @Autowired
    private ISongService songService;

    @Autowired
    private IGenreService genreService;

    @Autowired
    private ISingerService singerService;

    @GetMapping("song/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }

    @PostMapping("song")
    public ResponseEntity<Song> createNewSong(@RequestBody SongDTO songDTO){
        Song song = new Song();
        song.setName(songDTO.getName());
        song.setAuthor(songDTO.getAuthor());
        Optional<Genre> genre = genreService.findById(songDTO.getGenres());
        Optional<Singer> singer = singerService.findById(songDTO.getSingers());
        song.getGenres().add(genre.get());
        song.getSingers().add(singer.get());
        song.setListenCount(0);
        song.setLikes(0);
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }
    @GetMapping("song/new")
    public ResponseEntity<?> getSongOderByCrateAt(@RequestParam int offset,@RequestParam int limit){
        return new ResponseEntity<>(songService.findAllOrderByCreatedAt(PageRequest.of(offset,limit)).iterator(),HttpStatus.OK);
    }

}
