package com.example.casestudy.service.singer;

import com.example.casestudy.model.Singer;
import com.example.casestudy.service.IGeneralService;

import java.util.Optional;

public interface ISingerService extends IGeneralService<Singer> {
    Optional<Singer> findSingerByName(String name);

}
