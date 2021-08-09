package com.example.casestudy.service.playlistinteraction;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.repository.IPlaylistInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Iterable<PlaylistInteraction> findPlaylistComment(Long id) {
        return playlistInteractionRepository.findPlaylistComment(id);
    }
}
