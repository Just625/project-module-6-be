package com.example.casestudy.controller;

import com.example.casestudy.model.Genre;
import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.PlaylistDTO;
import com.example.casestudy.model.User;
import com.example.casestudy.service.genre.IGenreService;
import com.example.casestudy.service.playlist.IPlaylistService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/playlists")
@CrossOrigin("*")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;
    @Autowired
    private IGenreService genreService;
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<Playlist> save(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist.setDescription(playlistDTO.getDescription());
        Set<Genre> genres = new HashSet<>();
        genres.add(playlistDTO.getGenres());
        playlist.setGenres(genres);
        playlist.setUser(playlistDTO.getUser());
        playlist.setImgUrl(playlistDTO.getImgUrl());
        return new ResponseEntity<>(playlistService.save(playlist), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findPlaylistByUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            return new ResponseEntity<>(playlistService.getPlaylistByUserId(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPlaylistById(@PathVariable Long id) {
        Optional<Playlist> playlistOptional = playlistService.findById(id);
        if (playlistOptional.isPresent()) {
            return new ResponseEntity<>(playlistOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
        Optional<Playlist> playlistOptional = playlistService.findById(id);
        if (playlistOptional.isPresent()) {
            playlistService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        return new ResponseEntity<>(playlistService.findPlaylistByNameContainsAndUserContainsAndGenresContains(
                playlistDTO.getName(), playlistDTO.getUser().getId(), playlistDTO.getGenres().getId()), HttpStatus.OK);
    }

    @GetMapping("/searchByName/{name}")
    public ResponseEntity<?> searchPlayListByName(@PathVariable String name) {
        return new ResponseEntity<>(playlistService.findPlaylistByNameContains(name), HttpStatus.OK);
    }

    @GetMapping("/searchByUsername/{userName}/{listName}")
    public ResponseEntity<?> searchPlaylistByUserName(@PathVariable String userName, @PathVariable String listName) {
        return new ResponseEntity<>(playlistService.findByGenre_Name(userName, listName), HttpStatus.OK);
    }

    @GetMapping("/searchByDate/{startDate}/{endDate}")
    public ResponseEntity<Iterable<Playlist>> searchPlaylistDate(@PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
        Date a = formatter2.parse(startDate);
        Date b = formatter2.parse(endDate);
        return new ResponseEntity<>(playlistService.findPlaylistByCreatedAtBetween(a, b), HttpStatus.OK);
    }

//    @GetMapping("/searchAdvanced/{genre}/{name}/{startDate}/{endDate}")
//    public ResponseEntity<Iterable<Playlist>> searchPlaylistA(@PathVariable String genre, @PathVariable String name, @PathVariable String startDate, @PathVariable String endDate) throws ParseException {
//        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
//        Date a = formatter2.parse(startDate);
//        Date b = formatter2.parse(endDate);
//        return new ResponseEntity<>(playlistService.findByGenres_NameAndNameContainsAndCreatedAtBetween(genre, name, a, b), HttpStatus.OK);
//    }
    @GetMapping("/searchAdvanced/{genre}/{name}/{startDate}/{endDate}/{userId}")
    public ResponseEntity<Iterable<Playlist>> searchPlaylistA(@PathVariable String genre, @PathVariable String name, @PathVariable String startDate, @PathVariable String endDate, @PathVariable Long userId) throws ParseException {
        Optional<User> userOptional = userService.findById(userId);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter2.parse(startDate);
        Date b = formatter2.parse(endDate);
        return new ResponseEntity<>(playlistService.findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(genre, name, a, b,user ), HttpStatus.OK);
    }
}
