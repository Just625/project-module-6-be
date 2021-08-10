package com.example.casestudy.controller;
import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.model.PlaylistInteractionDTO;
import com.example.casestudy.service.PlaylistInteraction.IPlaylistInteractionService;
import com.example.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/playlistInteractions")
public class PlaylistInteractionController {
    @Autowired
    private IPlaylistInteractionService playlistInteractionService;

    @Autowired
    private IUserService userService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getFavouritesByUser(@PathVariable Long userId){
        return new ResponseEntity<>(playlistInteractionService.findLikeBySenderId(userId), HttpStatus.OK);
    }
    @GetMapping("/playlistId/{playlistId}")
    public ResponseEntity<?> getFavouritesByPlaylist(@PathVariable Long playlistId){
        return new ResponseEntity<>(playlistInteractionService.findLikeByPlaylistId(playlistId), HttpStatus.OK);
    }
    @GetMapping("/userId/{userId}/playlistId/{playlistId}")
    public ResponseEntity<?> getFavouriteByUserAndPlaylistId(@PathVariable Long userId, @PathVariable Long playlistId){
            return new ResponseEntity<>(playlistInteractionService.findLikeBySenderIdAndPlaylistId(userId,playlistId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewFavouritePlaylist(@RequestBody PlaylistInteractionDTO interactionDTO){
        Optional<PlaylistInteraction> interactionOptional = playlistInteractionService.findLikeBySenderIdAndPlaylistId(interactionDTO.getSenderId(), interactionDTO.getPlaylistId());
        if(interactionOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PlaylistInteraction interaction = new PlaylistInteraction();
        setData(interactionDTO, interaction);
        return new ResponseEntity<>(playlistInteractionService.save(interaction),HttpStatus.OK);
    }
    private void setData(PlaylistInteractionDTO interactionDTO, PlaylistInteraction interaction) {
        interaction.setSenderId(interactionDTO.getSenderId());
        interaction.setPlaylistId(interactionDTO.getPlaylistId());
        interaction.setRecieverId(interactionDTO.getRecieverId());
        interaction.setComment(interactionDTO.getComment());
        interaction.setLink(interactionDTO.getLink());
        interaction.setLikes(interactionDTO.isLikes());
        interaction.setRead(interactionDTO.isRead());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFavouritePlaylist(@PathVariable Long id,@RequestBody PlaylistInteractionDTO interactionDTO){
        Optional<PlaylistInteraction> interactionOptional = playlistInteractionService.findById(id);
        if(interactionOptional.isPresent()){
            setData(interactionDTO, interactionOptional.get());
            return new ResponseEntity<>(playlistInteractionService.save(interactionOptional.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
