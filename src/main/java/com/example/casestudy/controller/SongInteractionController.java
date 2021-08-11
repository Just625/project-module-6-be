package com.example.casestudy.controller;

import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SingerInteractionDTO;
import com.example.casestudy.model.SongInteraction;
import com.example.casestudy.model.SongInteractionDTO;
import com.example.casestudy.service.songinteraction.ISongInteractionService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/songInteractions")
public class SongInteractionController {
    @Autowired
    private ISongInteractionService songInteractionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getLikeByUser(@PathVariable Long userId){
        return new ResponseEntity<>(songInteractionService.findLikeBySenderId(userId), HttpStatus.OK);
    }
    @GetMapping("/songId/{songId}")
    public ResponseEntity<?> getLikeBySong(@PathVariable Long songId){
        return new ResponseEntity<>(songInteractionService.findLikeBySongId(songId), HttpStatus.OK);
    }
    @GetMapping("/userId/{userId}/songId/{songId}")
    public ResponseEntity<?> getLikeByUserAndSong(@PathVariable Long userId, @PathVariable Long songId){
        return new ResponseEntity<>(songInteractionService.findLikeBySenderIdAndSong(userId, songId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewLikeSong(@RequestBody SongInteractionDTO interactionDTO){
        Optional<SongInteraction> interactionOptional = songInteractionService.findLikeBySenderIdAndSong(interactionDTO.getSenderId(), interactionDTO.getSongId());
        if(interactionOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SongInteraction interaction = new SongInteraction();
        setData(interactionDTO, interaction);
        return new ResponseEntity<>(songInteractionService.save(interaction),HttpStatus.OK);
    }
    private void setData(SongInteractionDTO interactionDTO, SongInteraction interaction) {
        interaction.setSenderId(interactionDTO.getSenderId());
        interaction.setSongId(interactionDTO.getSongId());
        interaction.setReceiverId(interactionDTO.getReceiverId());
        interaction.setComment(interactionDTO.getComment());
        interaction.setLink(interactionDTO.getLink());
        interaction.setLikes(interactionDTO.isLikes());
        interaction.setRead(interactionDTO.isRead());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLikeSong(@PathVariable Long id,@RequestBody SongInteractionDTO interactionDTO){
        Optional<SongInteraction> interactionOptional = songInteractionService.findById(id);
        if(interactionOptional.isPresent()){
            setData(interactionDTO, interactionOptional.get());
            return new ResponseEntity<>(songInteractionService.save(interactionOptional.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
