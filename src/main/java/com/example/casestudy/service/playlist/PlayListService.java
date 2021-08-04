package com.example.casestudy.service.playlist;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.repository.IPlaylistRepository;
import com.example.casestudy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PlayListService implements IPlaylistService {
    @Autowired
    private IPlaylistRepository playlistRepository;
    @Autowired
    private IUserRepository userRepository;
    @Override
    public Iterable<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public void deleteById(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Iterable<Playlist> getPlaylistByUserId(Long id) {
            return playlistRepository.findPlaylistByUserId(id);
    }
}
