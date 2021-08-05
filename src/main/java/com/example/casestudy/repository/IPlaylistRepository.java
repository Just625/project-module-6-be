package com.example.casestudy.repository;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    Iterable<Playlist> findPlaylistByUserId(Long id);
    Iterable<Playlist> findPlaylistByNameContainsAndUserContainsAndGenresContains(String name, Long id, Long id2);
    Iterable<Playlist> findPlaylistByNameContains(String name);
    Iterable<Playlist> findByGenres_NameAndNameContains(String s, String name);
    Iterable<Playlist> findPlaylistByCreatedAtBetween(Date startDate, Date endDate);
    Iterable<Playlist> findByGenres_NameAndNameContainsAndCreatedAtBetween(String name, String listName, Date startDate, Date endDate);
    Iterable<Playlist> findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(String name, String listName, Date startDate, Date endDate, User user);
}
