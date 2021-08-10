package com.example.casestudy.controller;

import com.example.casestudy.model.*;
import com.example.casestudy.service.genre.IGenreService;
import com.example.casestudy.service.playlist.IPlaylistService;
import com.example.casestudy.service.playlistinteraction.IPlaylistInteractionService;
import com.example.casestudy.service.song.ISongService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @Autowired
    private ISongService songService;
    @Autowired
    private IPlaylistInteractionService playlistInteractionService;

    @PostMapping
    public ResponseEntity<Playlist> save(@Validated @RequestBody PlaylistDTO playlistDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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

    @PutMapping("/{id}")
    public ResponseEntity<?> editPlayListInfo(@PathVariable Long id, @Validated @RequestBody PlaylistDTO playlistDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Optional<Playlist> playlistOptional = playlistService.findById(id);
        if (playlistOptional.isPresent()) {
            Playlist playlist = playlistOptional.get();
            playlist.setName(playlistDTO.getName());
            playlist.setDescription(playlistDTO.getDescription());
            Set<Genre> genres = new HashSet<>();
            genres.add(playlistDTO.getGenres());
            playlist.setGenres(genres);
            playlist.setImgUrl(playlistDTO.getImgUrl());
            playlist.setLastUpdated(new Date());
            return new ResponseEntity<>(playlistService.save(playlist), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{songId}/{playlistId}")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long songId, @PathVariable Long playlistId) {
        Optional<Playlist> playlistOptional = playlistService.findById(playlistId);
        Optional<Song> songOptional = songService.findById(songId);
        if (playlistOptional.isPresent() && songOptional.isPresent()) {
            Set<Song> songs = playlistOptional.get().getSongs();
            songs.add(songOptional.get());
            playlistOptional.get().setSongs(songs);
            playlistOptional.get().setLastUpdated(new Date());
            return new ResponseEntity<>(playlistService.save(playlistOptional.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/searchByName/{name}")
    public ResponseEntity<?> searchPlayListByName(@PathVariable String name) {
        return new ResponseEntity<>(playlistService.findPlaylistByNameContains(name), HttpStatus.OK);
    }

    @GetMapping("/searchAdvanced/{genre}/{name}/{startDate}/{endDate}/{username}")
    public ResponseEntity<Iterable<Playlist>> searchPlaylistA(@PathVariable String genre, @PathVariable String name, @PathVariable String startDate, @PathVariable String endDate, @PathVariable String username) throws ParseException {
        User user = userService.findByUsername(username);
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter2.parse(startDate);
        Date b = formatter2.parse(endDate);
        return new ResponseEntity<>(playlistService.findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(genre, name, a, b, user), HttpStatus.OK);
    }

    @GetMapping("/toplisten")
    public ResponseEntity<?> getPlaylistByTopListen(@RequestParam int offset, @RequestParam int limit) {
        return new ResponseEntity<>(playlistService.findPlayListByListenCount(PageRequest.of(offset, limit)).iterator(), HttpStatus.OK);
    }

    @GetMapping("/findCommentById/{playlistId}")
    public ResponseEntity<?> findCommentByPlaylistId(@PathVariable Long playlistId, @RequestParam int page, @RequestParam int size) {
        Optional<Playlist> playlist = playlistService.findById(playlistId);
        if (!playlist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlistInteractionService.findPlaylistComment(playlistId, PageRequest.of(page, size)).iterator(), HttpStatus.OK);
    }

    @PostMapping("/addComment/{senderId}/{playlistId}/{comment}")
    public ResponseEntity<PlaylistInteraction> addComment(@PathVariable Long senderId, @PathVariable Long playlistId, @PathVariable String comment) {
        Optional<Playlist> playlistOptional = playlistService.findById(playlistId);
        Optional<User> senderOptional = userService.findById(senderId);
        if (!playlistOptional.isPresent() || !senderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Date createdAt = new Date();
        Long recieverId = playlistOptional.get().getUser().getId();
        String link = "http://localhost:4200/playlist/" + playlistId;
        PlaylistInteraction playlistInteraction = new PlaylistInteraction(senderId, recieverId, playlistId, comment, createdAt, link, false);
        return new ResponseEntity<>(playlistInteractionService.save(playlistInteraction), HttpStatus.OK);
    }
}
