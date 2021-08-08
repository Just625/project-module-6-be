package com.example.casestudy.service.song;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.Song;
import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISongService extends IGeneralService<Song> {
    Iterable<Song> findSongByUserId(Long userId);
    Iterable<Song> findSongByNameOrAuthor(String keyword, Long id);
    void deleteSongByIdAndUserId(Long userId, Long songId);
    Iterable<Song> findSongByNameContains(String name);
    Iterable<Song> findByNameContainsAndAuthorContainsAndSingers_IdAndUser(String songName, String authorName, Long singerId, User user);
    Iterable<Song> getTopSong();

    Page<Song> findAllOrderByCreatedAt(Pageable pageable);
    List<Song> findSongsBySinger(Long singerId);


}
