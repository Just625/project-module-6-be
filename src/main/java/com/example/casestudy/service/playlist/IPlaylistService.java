package com.example.casestudy.service.playlist;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;

import java.util.Date;

public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> getPlaylistByUserId(Long id);

    Iterable<Playlist> findPlaylistByNameContains(String name);

    Iterable<Playlist> findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(String name, String listName, Date startDate, Date endDate, User user);

}
