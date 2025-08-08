package com.example.marvelexplorer.service.impl;

import com.example.marvelexplorer.dto.MyPageable;
import com.example.marvelexplorer.persistence.integration.marvel.dto.ComicDto;
import com.example.marvelexplorer.persistence.integration.marvel.repository.ComicRepository;
import com.example.marvelexplorer.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public List<ComicDto> findAll(MyPageable pageable, Long characterId) {
        return comicRepository.findAll(pageable, characterId);
    }

    @Override
    public ComicDto findById(Long comicId) {
        return comicRepository.findById(comicId);
    }
}
