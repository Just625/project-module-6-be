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

import java.util.*;

@RestController
@CrossOrigin("*")
public class SongController {
    @Autowired
    private IUserService userService;

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
        song.setAlbum(songDTO.getAlbum());
        song.setDescription(songDTO.getDescription());
        song.setImgUrl(songDTO.getImgUrl());
        song.setFileMp3(songDTO.getMp3Url());
        Optional<User> user = userService.findById(songDTO.getUserId());
        Optional<Genre> genre = genreService.findById(songDTO.getGenres());
        Optional<Singer> singer = singerService.findById(songDTO.getSingers());
        song.getGenres().add(genre.get());
        song.getSingers().add(singer.get());
        song.setUser(user.get());
        song.setListenCount(0);
        song.setLikes(0);
        List commentList = new ArrayList<Comment>();
        song.setCommentList(commentList);
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }

    @GetMapping("song/userId/{id}")
    public ResponseEntity<Iterable<Song>> getAllSongByUserId(@PathVariable Long id){
        List<Song> songs = (List<Song>) this.songService.findSongByUserId(id);
        if (!songs.isEmpty()){
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("song/search/{keyword}/{id}")
    public ResponseEntity<Iterable<Song>> getSongByNameOrAuthor(@PathVariable String keyword, @PathVariable Long id){
        List<Song> songs = (List<Song>) this.songService.findSongByNameOrAuthor(keyword, id);
        if (!songs.isEmpty()){
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
