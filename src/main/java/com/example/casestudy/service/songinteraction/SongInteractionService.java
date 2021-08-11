package com.example.casestudy.service.songinteraction;

import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.model.SongInteraction;
import com.example.casestudy.repository.ISongInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SongInteractionService implements ISongInteractionService{
    @Autowired
    private ISongInteractionRepository songInteractionRepository;
    @Override
    public Iterable<SongInteraction> findAll() {
        return songInteractionRepository.findAll();
    }

    @Override
    public Optional<SongInteraction> findById(Long id) {
        return songInteractionRepository.findById(id);
    }

    @Override
    public SongInteraction save(SongInteraction songInteraction) {
        return songInteractionRepository.save(songInteraction);
    }

    @Override
    public void deleteById(Long id) {
         songInteractionRepository.deleteById(id);
    }

    @Override
    public Optional<SongInteraction> findLikeBySenderIdAndSong(Long senderId, Long songId) {
        return songInteractionRepository.findLikeBySenderIdAndSong(senderId, songId);
    }

    @Override
    public Iterable<SongInteraction> findLikeBySenderId(Long senderId) {
        return songInteractionRepository.findLikeBySenderId(senderId);
    }

    @Override
    public Iterable<SongInteraction> findLikeBySongId(Long songId) {
        return songInteractionRepository.findLikeBySongId(songId);
    }

    @Override
    public Page<SongInteraction> findSongComment(Long id, Pageable pageable) {
        return songInteractionRepository.findSongComment(id, pageable);
    }
}
