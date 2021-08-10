package com.example.casestudy.controller;

import com.example.casestudy.model.*;
import com.example.casestudy.service.genre.IGenreService;
import com.example.casestudy.service.singer.ISingerService;
import com.example.casestudy.service.song.ISongService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<SongDTO> getSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SongDTO songDTO = new SongDTO();
        songDTO.setId(song.get().getId());
        songDTO.setName(song.get().getName());
        songDTO.setAlbum(song.get().getAlbum());
        songDTO.setAuthor(song.get().getAuthor());
        songDTO.setDescription(song.get().getDescription());
        songDTO.setImgUrl(song.get().getImgUrl());
        Set<Singer> singers = song.get().getSingers();
        Long singerId = null;
        for (Singer singer : singers) {
            singerId = singer.getId();
            break;
        }
        songDTO.setSingers(singerId);
        Set<Genre> genres = song.get().getGenres();
        Long genreId = null;
        for (Genre genre : genres) {
            genreId = genre.getId();
            break;
        }
        songDTO.setGenres(genreId);
        songDTO.setMp3Url(song.get().getFileMp3());
        songDTO.setUserId(song.get().getUser().getId());
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> songOptional = songService.findById(id);
        if(!songOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(songOptional.get(), HttpStatus.OK);
    }

    @PostMapping("song")
    public ResponseEntity<Song> createNewSong(@RequestBody SongDTO songDTO) {
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
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }

    @GetMapping("song/userId/{id}")
    public ResponseEntity<Iterable<Song>> getAllSongByUserId(@PathVariable Long id) {
        List<Song> songs = (List<Song>) this.songService.findSongByUserId(id);
        if (!songs.isEmpty()) {
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("song/new")
    public ResponseEntity<?> getSongOderByCrateAt(@RequestParam int offset, @RequestParam int limit) {
        return new ResponseEntity<>(songService.findAllOrderByCreatedAt(PageRequest.of(offset, limit)).iterator(), HttpStatus.OK);
    }


    @GetMapping("song/search/{keyword}/{id}")
    public ResponseEntity<Iterable<Song>> getSongByNameOrAuthor(@PathVariable String keyword, @PathVariable Long id) {
        List<Song> songs = (List<Song>) this.songService.findSongByNameOrAuthor(keyword, id);
        if (!songs.isEmpty()) {
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("song/{userId}/{songId}")
    public ResponseEntity<?> deleteSongByUserIdAndSongId(@PathVariable Long userId, @PathVariable Long songId) {
        try {
            this.songService.deleteSongByIdAndUserId(userId, songId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("song")
    public ResponseEntity<Song> updateSong(@RequestBody SongDTO songDTO) {
        Optional<Song> song = this.songService.findById(songDTO.getId());
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        song.get().setName(songDTO.getName());
        song.get().setAlbum(songDTO.getAlbum());
        song.get().setDescription(songDTO.getDescription());
        song.get().setAuthor(songDTO.getAuthor());
        song.get().setFileMp3(songDTO.getMp3Url());
        song.get().setImgUrl(songDTO.getImgUrl());
        Set<Genre> genres = new HashSet<>();
        Optional<Genre> genre = this.genreService.findById(songDTO.getGenres());
        if (genre.isPresent()) {
            genres.add(genre.get());
            song.get().setGenres(genres);
        }
        Set<Singer> singers = new HashSet<>();
        Optional<Singer> singer = this.singerService.findById(songDTO.getSingers());
        if (singer.isPresent()) {
            singers.add(singer.get());
            song.get().setSingers(singers);
        }
        this.songService.save(song.get());
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }

    @GetMapping("songs/findByName/{songName}")
    public ResponseEntity<Iterable<Song>> findSongByName(@PathVariable String songName) {
        return new ResponseEntity<>(songService.findSongByNameContains(songName), HttpStatus.OK);
    }

    @GetMapping("/songs/findSongAdvanced/{songName}/{authorName}/{singerId}/{userId}")
    public ResponseEntity<Iterable<Song>> findSongFull(@PathVariable String songName, @PathVariable String authorName, @PathVariable Long singerId, @PathVariable Long userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(songService.findByNameContainsAndAuthorContainsAndSingers_IdAndUser(songName, authorName, singerId, userOptional.get()), HttpStatus.OK);
    }

    @GetMapping("toplisten")
    public ResponseEntity<Iterable<Song>> getTopSong() {
        return new ResponseEntity<>(this.songService.getTopSong(), HttpStatus.OK);
    }

    @GetMapping("/songs/findSongFull/{songName}/{userName}/{genreName}/{startDate}/{endDate}")
    public ResponseEntity<Iterable<Song>> findSongFullAnother(@PathVariable String songName, @PathVariable String userName, @PathVariable String genreName, @PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        User user = userService.findByUsername(userName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDay = formatter.parse(startDate);
        Date endDay = formatter.parse(endDate);
        return new ResponseEntity<>(songService.findByNameContainsAndUserAndGenres_NameAndCreatedAtBetween(songName, user, genreName, startDay, endDay), HttpStatus.OK);
    }

    @GetMapping("most_likes")
    public ResponseEntity<?> getSongByLikes(@RequestParam int offset, @RequestParam int limit) {
        return new ResponseEntity<>(songService.findSongByLikes(PageRequest.of(offset, limit)).iterator(), HttpStatus.OK);
    }
}