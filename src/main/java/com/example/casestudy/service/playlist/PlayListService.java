package com.example.casestudy.service.playlist;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.Song;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.IPlaylistRepository;
import com.example.casestudy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
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


    @Override
    public Iterable<Playlist> findPlaylistByNameContains(String name) {
        return playlistRepository.findPlaylistByNameContains(name);
    }

    @Override
    public Iterable<Playlist> findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(String name, String listName, Date startDate, Date endDate, User user) {
        return this.playlistRepository.findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(name, listName, startDate, endDate, user);
    }

    @Override
    public Page<Playlist> findPlayListByListenCount(Pageable pageable) {
        return this.playlistRepository.findPlayListByListenCount(pageable);
    }


}
