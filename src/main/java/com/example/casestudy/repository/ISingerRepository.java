package com.example.casestudy.repository;

import com.example.casestudy.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISingerRepository extends JpaRepository<Singer,Long> {
    Optional<Singer> findSingerByName(String name);
}
