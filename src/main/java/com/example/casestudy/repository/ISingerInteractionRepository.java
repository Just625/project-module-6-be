package com.example.casestudy.repository;

import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SongInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISingerInteractionRepository extends JpaRepository<SingerInteraction, Long> {
    @Query(value= "select * from singer_interaction where comment > '' AND singer_id = ?1 order by created_at desc", nativeQuery = true)
    Page<SingerInteraction> findSingerComment(Long id, Pageable pageable);
}
