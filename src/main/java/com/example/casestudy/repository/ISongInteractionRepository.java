package com.example.casestudy.repository;

import com.example.casestudy.model.SongInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISongInteractionRepository extends JpaRepository<SongInteraction, Long> {
    @Query(value= "select * from song_interaction where comment > '' AND song_id = ?1 order by created_at desc", nativeQuery = true)
    Page<SongInteraction> findSongComment(Long id, Pageable pageable);
}
