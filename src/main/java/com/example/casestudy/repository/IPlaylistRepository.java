package com.example.casestudy.repository;

import com.example.casestudy.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaylistRepository extends JpaRepository<Playlist, Long> {
}
