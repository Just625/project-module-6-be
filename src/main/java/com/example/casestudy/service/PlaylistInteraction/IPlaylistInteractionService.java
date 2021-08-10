package com.example.casestudy.service.PlaylistInteraction;


import com.example.casestudy.model.PlaylistInteraction;

import com.example.casestudy.service.IGeneralService;

import java.util.Optional;

public interface IPlaylistInteractionService extends IGeneralService<PlaylistInteraction> {
    Optional<PlaylistInteraction> findLikeBySenderIdAndPlaylistId(Long senderId, Long playlistID);
    Iterable<PlaylistInteraction> findLikeBySenderId(Long senderId);
    Iterable<PlaylistInteraction> findLikeByPlaylistId(Long playlistId);


}
