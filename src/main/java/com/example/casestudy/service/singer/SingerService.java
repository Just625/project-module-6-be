package com.example.casestudy.service.singer;

import com.example.casestudy.model.Genre;
import com.example.casestudy.model.Singer;
import com.example.casestudy.model.User;
import com.example.casestudy.repository.ISingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Iterable<Singer> findByNameContains(String singerName) {
        return singerRepository.findByNameContains(singerName);
    }

    @Override
    public Iterable<Singer> findSingerByNameContainsAndUserAndGenres_NameAndAndDateOfBirthBetween(String singerName, User user, String genreName, Date startDate, Date endDate) {
        return singerRepository.findSingerByNameContainsAndUserAndGenres_NameAndAndDateOfBirthBetween(singerName, user, genreName, startDate, endDate);
    }
}
