package com.example.casestudy.service.singer;

import com.example.casestudy.model.Genre;
import com.example.casestudy.model.Singer;
import com.example.casestudy.repository.ISingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerService implements ISingerService {
    @Autowired
    private ISingerRepository singerRepository;

    @Override
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void deleteById(Long id) {
        singerRepository.deleteById(id);
    }
}
