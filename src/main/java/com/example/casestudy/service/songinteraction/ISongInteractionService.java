package com.example.casestudy.service.songinteraction;

import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SongInteraction;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISongInteractionService extends IGeneralService<SongInteraction> {
    Page<SongInteraction> findSongComment(Long id, Pageable pageable);
    Optional<SongInteraction> findLikeBySenderIdAndSong(Long senderId, Long songId);

    Iterable<SongInteraction> findLikeBySenderId(Long senderId);

    Iterable<SongInteraction> findLikeBySongId(Long songId);
}
