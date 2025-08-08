package com.example.marvelexplorer.persistence.integration.marvel.dto;

public record ComicDto(
        Long id,
        String title,
        String description,
        String modify,
        String resourceURI,
        ThumbnailDTO thumbnail
) {
}
