package com.example.casestudy.service.playlistinteraction;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.repository.IPlaylistInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistInteractionService implements IPlaylistInteractionService {
    @Autowired
    private IPlaylistInteractionRepository playlistInteractionRepository;


    @Override
    public Iterable<PlaylistInteraction> findAll() {
        return playlistInteractionRepository.findAll();
    }

    @Override
    public Optional<PlaylistInteraction> findById(Long id) {
        return playlistInteractionRepository.findById(id);
    }

    @Override
    public PlaylistInteraction save(PlaylistInteraction playlistInteraction) {
        return playlistInteractionRepository.save(playlistInteraction);
    }

    @Override
    public void deleteById(Long id) {
        playlistInteractionRepository.deleteById(id);
    }


    @Override
    public Optional<PlaylistInteraction> findLikeBySenderIdAndPlaylistId(Long senderId, Long playlistID) {
        return playlistInteractionRepository.findLikeBySenderIdAndPlaylistId(senderId, playlistID);
    }

    @Override
    public Iterable<PlaylistInteraction> findLikeBySenderId(Long senderId) {
        return playlistInteractionRepository.findLikeBySenderId(senderId);
    }

    @Override
    public Page<PlaylistInteraction> findPlaylistComment(Long id, Pageable pageable) {
        return playlistInteractionRepository.findPlaylistComment(id, pageable);
    }

    @Override
    public Iterable<PlaylistInteraction> findLikeByPlaylistId(Long playlistId) {
        return playlistInteractionRepository.findLikeByPlaylistId(playlistId);
    }
}
