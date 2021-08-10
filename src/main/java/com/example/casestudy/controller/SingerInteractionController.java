package com.example.casestudy.controller;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.model.PlaylistInteractionDTO;
import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SingerInteractionDTO;
import com.example.casestudy.service.playlistinteraction.IPlaylistInteractionService;
import com.example.casestudy.service.singerinteraction.ISingerInteractionService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/singerInteractions")
public class SingerInteractionController {
    @Autowired
    private ISingerInteractionService singerInteractionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getFavouritesByUser(@PathVariable Long userId){
        return new ResponseEntity<>(singerInteractionService.findLikeBySenderId(userId), HttpStatus.OK);
    }
    @GetMapping("/singer/{singerId}")
    public ResponseEntity<?> getFavouritesBySinger(@PathVariable Long singerId){
        return new ResponseEntity<>(singerInteractionService.findLikeBySingerId(singerId), HttpStatus.OK);
    }
    @GetMapping("/userId/{userId}/singer/{singerId}")
    public ResponseEntity<?> getFavouriteByUserAndSinger(@PathVariable Long userId, @PathVariable Long singerId){
        return new ResponseEntity<>(singerInteractionService.findLikeBySenderIdAndSingerId(userId,singerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewFavouriteSinger(@RequestBody SingerInteractionDTO interactionDTO){
        Optional<SingerInteraction> interactionOptional = singerInteractionService.findLikeBySenderIdAndSingerId(interactionDTO.getSenderId(), interactionDTO.getSingerId());
        if(interactionOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SingerInteraction interaction = new SingerInteraction();
        setData(interactionDTO, interaction);
        return new ResponseEntity<>(singerInteractionService.save(interaction),HttpStatus.OK);
    }
    private void setData(SingerInteractionDTO interactionDTO, SingerInteraction interaction) {
        interaction.setSenderId(interactionDTO.getSenderId());
        interaction.setSingerId(interactionDTO.getSingerId());
        interaction.setRecieverId(interactionDTO.getRecieverId());
        interaction.setComment(interactionDTO.getComment());
        interaction.setLink(interactionDTO.getLink());
        interaction.setLikes(interactionDTO.isLikes());
        interaction.setRead(interactionDTO.isRead());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFavouriteSinger(@PathVariable Long id,@RequestBody SingerInteractionDTO interactionDTO){
        Optional<SingerInteraction> interactionOptional = singerInteractionService.findById(id);
        if(interactionOptional.isPresent()){
            setData(interactionDTO, interactionOptional.get());
            return new ResponseEntity<>(singerInteractionService.save(interactionOptional.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
