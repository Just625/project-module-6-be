package com.example.casestudy.repository;

import com.example.casestudy.model.SingerInteraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISingerInteractionRepository extends JpaRepository<SingerInteraction, Long> {
}
