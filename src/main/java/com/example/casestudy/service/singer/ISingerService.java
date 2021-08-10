package com.example.casestudy.service.singer;

import com.example.casestudy.model.Singer;
import com.example.casestudy.model.User;
import com.example.casestudy.service.IGeneralService;

import java.util.Optional;

import java.util.Date;

public interface ISingerService extends IGeneralService<Singer> {
    Optional<Singer> findSingerByName(String name);

    Iterable<Singer> findByNameContains(String singerName);
    Iterable<Singer> findSingerByNameContainsAndUserAndGenres_NameAndAndDateOfBirthBetween(String singerName, User user, String genreName, Date startDate, Date endDate);
}
