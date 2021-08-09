package com.example.casestudy.repository;

import com.example.casestudy.model.Singer;
import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ISingerRepository extends JpaRepository<Singer,Long> {
    Iterable<Singer> findByNameContains(String singerName);
    Iterable<Singer> findSingerByNameContainsAndUserAndGenres_NameAndAndDateOfBirthBetween(String singerName, User user, String genreName, Date startDate, Date endDate);

}
