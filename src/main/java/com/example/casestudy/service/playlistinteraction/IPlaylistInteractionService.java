package com.example.casestudy.service.playlistinteraction;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.service.IGeneralService;

public interface IPlaylistInteractionService extends IGeneralService<PlaylistInteraction> {
Iterable<PlaylistInteraction> findPlaylistComment(Long id);
}
