package com.example.casestudy.controller;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.PlaylistDTO;
import com.example.casestudy.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
@CrossOrigin("*")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;
    @PostMapping
    public ResponseEntity<Playlist> save(@RequestBody PlaylistDTO playlistDTO){
        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist.setDescription(playlistDTO.getDescription());
        playlist.setGenres(playlistDTO.getGenres());
        playlist.setUser(playlistDTO.getUser());
        return new ResponseEntity<>(playlistService.save(playlist), HttpStatus.CREATED);
    }
}
