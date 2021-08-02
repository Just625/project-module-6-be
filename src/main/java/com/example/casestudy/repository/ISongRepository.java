package com.example.casestudy.repository;

import com.example.casestudy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISongRepository extends JpaRepository<Song, Long> {
    @Query("select s from Song s where s.user.id = ?1")
    Iterable<Song> findSongByUserId(Long userId);

    @Query("select s from Song s where s.user.id = ?2 and (s.name like CONCAT('%',?1,'%') or s.author like CONCAT('%',?1,'%'))")
    Iterable<Song> findSongByNameOrAuthor(String keyword, Long id);
}
