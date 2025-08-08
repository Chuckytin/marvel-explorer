package com.example.marvelexplorer.service;

import com.example.marvelexplorer.dto.MyPageable;
import com.example.marvelexplorer.persistence.integration.marvel.dto.ComicDto;

import java.util.List;

public interface ComicService {
    List<ComicDto> findAll(MyPageable pageable, Long characterId);

    ComicDto findById(Long comicId);
}
