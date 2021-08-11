package com.example.casestudy.repository;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SongInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ISongInteractionRepository extends JpaRepository<SongInteraction, Long> {
    @Query(value= "select * from song_interaction where comment > '' AND playlist_id = ?1 order by created_at desc", nativeQuery = true)
    Page<SongInteraction> findPlaylistComment(Long id, Pageable pageable);

    @Query(value = "select * from song_interaction where sender_id = ?1 and song_id = ?2 and comment is null limit  1",nativeQuery = true)
    Optional<SongInteraction> findLikeBySenderIdAndSong(Long senderId, Long songId);

    @Query(value = "select * from song_interaction where sender_id = ?1 and likes = true",nativeQuery = true)
    Iterable<SongInteraction> findLikeBySenderId(Long senderId);

    @Query(value = "select * from song_interaction where song_id = ?1 and likes = true",nativeQuery = true)
    Iterable<SongInteraction> findLikeBySongId(Long songId);
    @Query(value= "select * from song_interaction where comment > '' AND song_id = ?1 order by created_at desc", nativeQuery = true)
    Page<SongInteraction> findSongComment(Long id, Pageable pageable);
}
