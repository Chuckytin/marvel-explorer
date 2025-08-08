package com.example.marvelexplorer.web.controller;

import com.example.marvelexplorer.dto.MyPageable;
import com.example.marvelexplorer.persistence.integration.marvel.dto.CharacterDto;
import com.example.marvelexplorer.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterDto>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    ) {

        return ResponseEntity.ok(characterService.findAll(createPageable(offset, limit), name, comics, series));
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDto.CharacterInfoDto> findInfoById(
            @PathVariable Long characterId
    ) {
        return ResponseEntity.ok(characterService.findInfoById(characterId));
    }

    /**
     *
     */
    private MyPageable createPageable(long offset, long limit) {
        return new MyPageable(offset, limit);
    }

}
