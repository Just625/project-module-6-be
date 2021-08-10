package com.example.casestudy.service.singerinteraction;

import com.example.casestudy.model.PlaylistInteraction;
import com.example.casestudy.model.SingerInteraction;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ISingerInteractionService extends IGeneralService<SingerInteraction> {

    Iterable<SingerInteraction> findLikeBySingerId(Long singerId);

    Iterable<SingerInteraction> findLikeBySenderId(Long senderId);

    Optional<SingerInteraction> findLikeBySenderIdAndSingerId(Long senderId, Long singerId);

}
