package com.ufpb.SwiftManga.src.service;

import com.ufpb.SwiftManga.src.dto.GenreDTO;
import com.ufpb.SwiftManga.src.model.Genre;
import com.ufpb.SwiftManga.src.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<GenreDTO> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public GenreDTO saveGenre(GenreDTO genreDTO) {
        Genre genre = convertToEntity(genreDTO);
        genre = genreRepository.save(genre);
        return convertToDTO(genre);
    }

    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    private GenreDTO convertToDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    private Genre convertToEntity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }
}
