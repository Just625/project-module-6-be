package com.example.casestudy.repository;

import com.example.casestudy.model.PlaylistInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlaylistInteractionRepository extends JpaRepository<PlaylistInteraction, Long> {
    @Query(value= "select * from playlist_interaction where comment > '' AND playlist_id = ?1 order by created_at desc", nativeQuery = true)
    Iterable<PlaylistInteraction> findPlaylistComment(Long id);
}
