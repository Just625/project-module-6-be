package com.example.casestudy.service.playlistinteraction;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPlaylistInteractionService extends IGeneralService<PlaylistInteraction> {
    Page<PlaylistInteraction> findPlaylistComment(Long id, Pageable pageable);

    Iterable<PlaylistInteraction> findLikeByPlaylistId(Long playlistId);

    Iterable<PlaylistInteraction> findLikeBySenderId(Long senderId);

    Optional<PlaylistInteraction> findLikeBySenderIdAndPlaylistId(Long senderId, Long playlistID);

}
