package com.example.casestudy.repository;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SongInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ISingerInteractionRepository extends JpaRepository<SingerInteraction, Long> {
    @Query(value= "select * from singer_interaction where comment > '' AND singer_id = ?1 order by created_at desc", nativeQuery = true)
    Page<SingerInteraction> findSingerComment(Long id, Pageable pageable);
    @Query(value = "select * from singer_interaction where sender_id = ?1 and singer_id = ?2 and comment is null limit  1",nativeQuery = true)
    Optional<SingerInteraction> findLikeBySenderIdAndSinger(Long senderId, Long singerId);

    @Query(value = "select * from singer_interaction where sender_id = ?1 and likes = true",nativeQuery = true)
    Iterable<SingerInteraction> findLikeBySenderId(Long senderId);

    @Query(value = "select * from singer_interaction where singer_id = ?1 and likes = true",nativeQuery = true)
    Iterable<SingerInteraction> findLikeBySingerId(Long singerId);
}
