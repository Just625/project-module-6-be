package com.example.casestudy.repository;

import com.example.casestudy.model.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISongRepository extends JpaRepository<Song, Long> {

    @Query("select s from Song s where s.user.id = ?1")
    Iterable<Song> findSongByUserId(Long userId);

    @Query("select s from Song s where s.user.id = ?2 and (s.name like CONCAT('%',?1,'%') or s.author like CONCAT('%',?1,'%'))")
    Iterable<Song> findSongByNameOrAuthor(String keyword, Long id);

    @Transactional
    @Modifying
    @Query("delete from Song s where s.user.id =?1 and s.id = ?2")
    void deleteSongByIdAndUserId(Long userId, Long songId);

    @Query(value = "select * from Song order by created_at desc ", nativeQuery = true)
    Page<Song> findAllOrderByCreatedAt(Pageable pageable);

    @Query(value = "select * from song order by listen_count desc limit 10 ", nativeQuery = true)
    Iterable<Song> getTopSong();

    Iterable<Song> findSongByNameContains(String name);

    Iterable<Song> findByNameContainsAndAuthorContainsAndSingers_IdAndUser(String songName, String authorName, Long singerId, User user);

}
