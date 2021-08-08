package com.example.casestudy.service.song;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.Song;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService{
    @Autowired
    private ISongRepository songRepository;

    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void deleteById(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Page<Song> findAllOrderByCreatedAt(Pageable pageable) {
        return songRepository.findAllOrderByCreatedAt(pageable);
    }

    @Override
    public Iterable<Song> findByNameContainsAndUserAndGenres_NameAndCreatedAtBetween(String songName, User user, String genreName, Date startDate, Date endDate) {
        return songRepository.findByNameContainsAndUserAndGenres_NameAndCreatedAtBetween(songName, user, genreName, startDate, endDate);
    }


    @Override
    public Iterable<Song> findSongByUserId(Long userId) {
        return songRepository.findSongByUserId(userId);
    }

    @Override
    public Iterable<Song> findSongByNameOrAuthor(String keyword, Long id) {
        return songRepository.findSongByNameOrAuthor(keyword, id);
    }

    @Override
    public void deleteSongByIdAndUserId(Long userId, Long songId) {
        songRepository.deleteSongByIdAndUserId(userId, songId);
    }

    @Override
    public Iterable<Song> findSongByNameContains(String name) {
        return songRepository.findSongByNameContains(name);
    }

    @Override
    public Iterable<Song> findByNameContainsAndAuthorContainsAndSingers_IdAndUser(String songName, String authorName, Long singerId, User user) {
        return songRepository.findByNameContainsAndAuthorContainsAndSingers_IdAndUser(songName, authorName, singerId, user);
    }

    @Override
    public Iterable<Song> getTopSong() {
        return songRepository.getTopSong();
    }

    @Override
    public List<Song> findSongsBySinger(Long singerId) {
        List<Long> ids = songRepository.findSongsBySinger(singerId);
        List<Song> songs = new ArrayList<>();
        for (Long id: ids) {
            Optional<Song> songOptional = findById(id);
            if(songOptional.isPresent()){
                songs.add(songOptional.get());
            }
        }
        return songs;
    }
}
