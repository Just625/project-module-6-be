package com.example.casestudy.repository;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    Iterable<Playlist> findPlaylistByUserId(Long id);
    Iterable<Playlist> findPlaylistByNameContainsAndUserContainsAndGenresContains(String name, Long id, Long id2);
    Iterable<Playlist> findPlaylistByNameContains(String name);
//    @Query("select p from Playlist p  join User on (p.user_id= User.id) where User.name like CONCAT('%',?1,'%'),na )
////    Iterable<Playlist> findPlaylistByNameUserGenreDate()
    Iterable<Playlist> findByGenres_NameAndNameContains(String s, String name);
}
