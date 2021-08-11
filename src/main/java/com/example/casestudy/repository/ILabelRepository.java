package com.example.casestudy.repository;

import com.example.casestudy.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILabelRepository extends JpaRepository<Label, Long> {
    @Query(value = "select name from label_songs join label l on l.id = label_songs.label_id where songs_id = ?1", nativeQuery = true)
    List<String> findSongTagsBySongId(Long songId);
    @Query(value = "select distinct s.id from song s join label_songs ls on id = ls.songs_id join label l on ls.label_id = l.id where l.name like CONCAT('%',?1,'%')", nativeQuery = true)
    List<Long> findSongIdByTagName(String tagName);
}
