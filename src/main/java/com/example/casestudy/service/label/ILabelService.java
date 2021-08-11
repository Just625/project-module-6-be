package com.example.casestudy.service.label;

import com.example.casestudy.model.Label;
import com.example.casestudy.service.IGeneralService;

import java.util.List;

public interface ILabelService extends IGeneralService<Label> {
    List<String> findSongTagsBySongId(Long songId);
    List<Long> findSongIdByTagName(String tagName);
}
