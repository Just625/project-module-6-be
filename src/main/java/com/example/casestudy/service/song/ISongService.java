package com.example.casestudy.service.song;

import com.example.casestudy.model.Song;
import com.example.casestudy.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISongService extends IGeneralService<Song> {
    Page<Song> findAllOrderByCreatedAt(Pageable pageable);


}
