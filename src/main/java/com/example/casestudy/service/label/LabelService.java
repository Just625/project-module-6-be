package com.example.casestudy.service.label;

import com.example.casestudy.model.Label;
import com.example.casestudy.repository.ILabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelService implements ILabelService{
    @Autowired
    private ILabelRepository labelRepository;

    @Override
    public Iterable<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public Optional<Label> findById(Long id) {
        return labelRepository.findById(id);
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void deleteById(Long id) {
        labelRepository.deleteById(id);
    }

    @Override
    public List<String> findSongTagsBySongId(Long songId) {
        return labelRepository.findSongTagsBySongId(songId);
    }

    @Override
    public List<Long> findSongIdByTagName(String tagName) {
        return labelRepository.findSongIdByTagName(tagName);
    }
}
