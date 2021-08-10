package com.example.casestudy.service.singerinteraction;

import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.repository.ISingerInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerInteractionService implements ISingerInteractionService {
     @Autowired
     private  ISingerInteractionRepository singerInteractionRepository;
    @Override
    public Iterable<SingerInteraction> findAll() {
        return singerInteractionRepository.findAll();
    }

    @Override
    public Optional<SingerInteraction> findById(Long id) {
        return singerInteractionRepository.findById(id);
    }

    @Override
    public SingerInteraction save(SingerInteraction singerInteraction) {
        return singerInteractionRepository.save(singerInteraction);
    }

    @Override
    public void deleteById(Long id) {
        singerInteractionRepository.deleteById(id);
    }

    @Override
    public Iterable<SingerInteraction> findLikeBySingerId(Long singerId) {
        return singerInteractionRepository.findLikeBySingerId(singerId);
    }

    @Override
    public Iterable<SingerInteraction> findLikeBySenderId(Long senderId) {
        return singerInteractionRepository.findLikeBySenderId(senderId);
    }

    @Override
    public Optional<SingerInteraction> findLikeBySenderIdAndSingerId(Long senderId, Long singerId) {
        return singerInteractionRepository.findLikeBySenderIdAndSinger(senderId, singerId);
    }
}
