package com.example.casestudy.controller;

import com.example.casestudy.model.Label;
import com.example.casestudy.model.Song;
import com.example.casestudy.service.label.ILabelService;
import com.example.casestudy.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/labels")
public class LabelController {
    @Autowired
    private ILabelService labelService;
    @Autowired
    private ISongService songService;

    @PostMapping("/addTag")
    public ResponseEntity<Label> addTagToSong(@RequestBody Label label) {
        return new ResponseEntity<>(labelService.save(label), HttpStatus.OK);
    }

    @PostMapping("/addTags/{songId}")
    public ResponseEntity<?> addTagsToSong(@RequestBody List<Label> labels, @PathVariable Long songId) {
        Optional<Song> songOptional = songService.findById(songId);
        if (!songOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for (Label label : labels) {
            Set<Song> songs = new HashSet<>();
            songs.add(songOptional.get());
            label.setSongs(songs);
            labelService.save(label);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getSongTag/{songId}")
    public ResponseEntity<List<String>> findSongTagsById(@PathVariable Long songId) {
        Optional<Song> songOptional = songService.findById(songId);
        if (!songOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(labelService.findSongTagsBySongId(songId), HttpStatus.OK);
    }

    @GetMapping("/findSongsByTagName/{tagName}")
    public ResponseEntity<List<Song>> findSongsByTagName(@PathVariable String tagName) {
        List<Long> songsId = labelService.findSongIdByTagName(tagName);
        List<Song> songs = new ArrayList<>();
        for (Long songId : songsId) {
            Optional<Song> songOptional = songService.findById(songId);
            if (songOptional.isPresent()) {
                songs.add(songOptional.get());
            }
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Label>> getLabelList() {
        return new ResponseEntity<>(labelService.findAll(), HttpStatus.OK);
    }
}
