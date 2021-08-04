package com.example.casestudy.service.playlist;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;

public interface IPlaylistService extends IGeneralService<Playlist> {
    Iterable<Playlist> getPlaylistByUserId(Long id);
    Iterable<Playlist> findPlaylistByNameContainsAndUserContainsAndGenresContains(String name, Long id, Long id2);
    Iterable<Playlist> findPlaylistByNameContains(String name);
}
