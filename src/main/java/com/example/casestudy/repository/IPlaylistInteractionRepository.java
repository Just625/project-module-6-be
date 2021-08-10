package com.example.casestudy.repository;

import com.example.casestudy.model.PlaylistInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IPlaylistInteractionRepository extends JpaRepository<PlaylistInteraction,Long> {
    @Query(value = "select * from playlist_interaction where sender_id = ?1 and playlist_id = ?2 and comment is null limit  1",nativeQuery = true)
    Optional<PlaylistInteraction> findLikeBySenderIdAndPlaylistId(Long senderId, Long playlistID);

    @Query(value = "select * from playlist_interaction where sender_id = ?1 and likes = true",nativeQuery = true)
    Iterable<PlaylistInteraction> findLikeBySenderId(Long senderId);

    @Query(value = "select * from playlist_interaction where playlist_id = ?1 and likes = true",nativeQuery = true)
    Iterable<PlaylistInteraction> findLikeByPlaylistId(Long playlistId);

    @Query(value= "select * from playlist_interaction where comment > '' AND playlist_id = ?1 order by created_at desc", nativeQuery = true)
    Page<PlaylistInteraction> findPlaylistComment(Long id, Pageable pageable);



}
