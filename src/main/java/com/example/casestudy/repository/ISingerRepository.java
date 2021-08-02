package com.example.casestudy.repository;

import com.example.casestudy.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISingerRepository extends JpaRepository<Singer, Long> {
}
