package com.example.casestudy.repository;

import com.example.casestudy.model.SongInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISongInteractionRepository extends JpaRepository<SongInteraction, Long> {
}
