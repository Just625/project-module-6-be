package com.example.casestudy.service.singerinteraction;

import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISingerInteractionService extends IGeneralService<SingerInteraction> {
    Page<SingerInteraction> findSingerComment(Long id, Pageable pageable);
}
