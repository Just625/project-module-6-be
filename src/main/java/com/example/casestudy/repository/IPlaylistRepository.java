package com.example.casestudy.repository;

import com.example.casestudy.model.Playlist;
import com.example.casestudy.model.Song;
import com.example.casestudy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
    Iterable<Playlist> findPlaylistByUserId(Long id);

    Iterable<Playlist> findPlaylistByNameContains(String name);

    Iterable<Playlist> findByGenres_NameAndNameContainsAndCreatedAtBetweenAndUser(String name, String listName, Date startDate, Date endDate, User user);

    @Query(value = "select * from playlist order by listen_count desc",nativeQuery = true)
    Page<Playlist> findPlayListByListenCount(Pageable pageable);

    @Query(value = "select * from playlist order by created_at desc", nativeQuery = true)
    Page<Playlist> findPlaylistByCreatedTime(Pageable pageable);

    @Query(value ="select * from playlist order by likes desc ", nativeQuery = true)
    Page<Playlist> findPlayListByLikes(Pageable pageable);
}
