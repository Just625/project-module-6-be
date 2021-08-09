package com.example.casestudy.service.playlist;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.Song;
import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> getPlaylistByUserId(Long id);

    Iterable<Playlist> findPlaylistByNameContains(String name);

    Iterable<Playlist> findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(String name, String listName, Date startDate, Date endDate, User user);

    Page<Playlist> findPlayListByListenCount(Pageable pageable);

    Page<Playlist> findPlaylistByCreatedTime(Pageable pageable);

    Page<Playlist> findPlayListByLikes(Pageable pageable);

}
