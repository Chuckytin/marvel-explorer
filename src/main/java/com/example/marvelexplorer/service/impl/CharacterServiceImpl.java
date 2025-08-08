package com.example.marvelexplorer.service.impl;

import com.example.marvelexplorer.dto.MyPageable;
import com.example.marvelexplorer.persistence.integration.marvel.dto.CharacterDto;
import com.example.marvelexplorer.persistence.integration.marvel.repository.CharacterRepository;
import com.example.marvelexplorer.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        return characterRepository.findAll(pageable, name, comics, series);
    }

    @Override
    public CharacterDto.CharacterInfoDto findInfoById(Long characterId) {
        return characterRepository.findInfoById(characterId);
    }
}
