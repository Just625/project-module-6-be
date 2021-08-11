package com.example.casestudy.controller;

import com.example.casestudy.model.*;
import com.example.casestudy.service.genre.IGenreService;
import com.example.casestudy.service.singer.ISingerService;
import com.example.casestudy.service.singerinteraction.ISingerInteractionService;
import com.example.casestudy.service.song.ISongService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private ISingerInteractionService singerInteractionService;

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
    public ResponseEntity<Singer> save(@RequestBody SingerDTO singerDTO) throws ParseException {
        Iterable<Singer> singers = singerService.findAll();
        for (Singer singer1 : singers) {
            if (singer1.getName().equals(singerDTO.getName())) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        Singer singer = new Singer();
        singer.setName(singerDTO.getName());
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
        Date dOB = formatter2.parse(singerDTO.getDateOfBirth());
        singer.setDateOfBirth(dOB);
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
    public ResponseEntity<?> getAllSongsOfSinger(@PathVariable Long id) {
        List<Song> songs = songService.findSongsBySinger(id);
        return new ResponseEntity<>(songs, HttpStatus.OK);
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

    @GetMapping("/findByName/{singerName}")
    public ResponseEntity<Iterable<Singer>> findSingerByName(@PathVariable String singerName) {
        return new ResponseEntity<>(singerService.findByNameContains(singerName), HttpStatus.OK);
    }

    @GetMapping("/findSingerAdvanced/{singerName}/{userName}/{genreName}/{startDate}/{endDate}")
    public ResponseEntity<Iterable<Singer>> findSingerAdvanced(@PathVariable String singerName, @PathVariable String userName, @PathVariable String genreName, @PathVariable String startDate, @PathVariable String endDate) throws ParseException {
        User user = userService.findByUsername(userName);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDay = formatter.parse(startDate);
        Date endDay = formatter.parse(endDate);
        return new ResponseEntity<>(singerService.findSingerByNameContainsAndUserAndGenres_NameAndAndDateOfBirthBetween(singerName, user, genreName, startDay, endDay), HttpStatus.OK);
    }
    @GetMapping("/findCommentById/{singerId}")
    public ResponseEntity<?> findCommentBySingerId(@PathVariable Long singerId, @RequestParam int page, @RequestParam int size) {
        Optional<Singer> singerOptional = singerService.findById(singerId);
        if (!singerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(singerInteractionService.findSingerComment(singerId, PageRequest.of(page, size)).iterator(), HttpStatus.OK);
    }

    @PostMapping("/addComment/{senderId}/{singerId}/{comment}")
    public ResponseEntity<SingerInteraction> addComment(@PathVariable Long senderId, @PathVariable Long singerId, @PathVariable String comment) {
        Optional<Singer> singerOptional = singerService.findById(singerId);
        Optional<User> senderOptional = userService.findById(senderId);
        if (!singerOptional.isPresent() || !senderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Date createdAt = new Date();
        Long recieverId = singerOptional.get().getUser().getId();
        String link = "http://localhost:4200/singer/" + singerId;
        SingerInteraction singerInteraction = new SingerInteraction(senderId, recieverId, singerId, comment, createdAt, link, false);
        return new ResponseEntity<>(singerInteractionService.save(singerInteraction), HttpStatus.OK);
    }
}
