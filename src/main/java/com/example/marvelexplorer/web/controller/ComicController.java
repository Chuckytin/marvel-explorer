package com.example.marvelexplorer.web.controller;

import com.example.marvelexplorer.dto.MyPageable;
import com.example.marvelexplorer.persistence.integration.marvel.dto.ComicDto;
import com.example.marvelexplorer.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @GetMapping
    public ResponseEntity<List<ComicDto>> findAll(
            @RequestParam(required = false) Long characterId,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit

    ) {
        return ResponseEntity.ok(comicService.findAll(createPageable(offset, limit), characterId));
    }

    /**
     *
     */
    private MyPageable createPageable(long offset, long limit) {
        return new MyPageable(offset, limit);
    }

    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDto> findById(
            @PathVariable Long comicId
    ) {
        return ResponseEntity.ok(comicService.findById(comicId));
    }

}
