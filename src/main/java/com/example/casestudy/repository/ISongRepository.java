package com.example.casestudy.repository;

import com.example.casestudy.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISongRepository extends JpaRepository<Song, Long> {
    @Query(value = "select * from Song order by created_at desc ",nativeQuery = true)
    Iterable<Song> findAllOrderByCreatedAt();
    @Query(value = "select * from Song order by listen_count desc ",nativeQuery = true)
    Iterable<Song> findAllOrderByListenCount();
}
