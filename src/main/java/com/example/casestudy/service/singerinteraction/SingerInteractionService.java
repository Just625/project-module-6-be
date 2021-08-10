package com.example.casestudy.service.songinteraction;

import com.example.casestudy.model.SongInteraction;
import com.example.casestudy.repository.ISongInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
