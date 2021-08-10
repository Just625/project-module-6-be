package com.example.casestudy.service.playlistinteraction;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlaylistInteractionService extends IGeneralService<PlaylistInteraction> {
    Page<PlaylistInteraction> findPlaylistComment(Long id, Pageable pageable);
}
