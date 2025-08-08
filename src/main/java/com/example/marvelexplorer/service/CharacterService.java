package com.example.marvelexplorer.service;

import com.example.marvelexplorer.dto.MyPageable;
import com.example.marvelexplorer.persistence.integration.marvel.dto.CharacterDto;

import java.util.List;

public interface CharacterService {

    List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series);

    CharacterDto.CharacterInfoDto findInfoById(Long characterId);
}
