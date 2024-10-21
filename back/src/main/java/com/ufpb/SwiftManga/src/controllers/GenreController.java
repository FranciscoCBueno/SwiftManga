package com.ufpb.SwiftManga.src.controllers;

import com.ufpb.SwiftManga.src.dto.GenreDTO;
import com.ufpb.SwiftManga.src.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.findAllGenres();
    }

    @PostMapping
    public GenreDTO createGenre(@RequestBody GenreDTO genreDTO) {
        return genreService.saveGenre(genreDTO);
    }

    @DeleteMapping("/{genreId}")
    public String deleteGenre(@PathVariable Long genreId) {
        genreService.deleteGenre(genreId);
        return "Gênero removido com sucesso.";
    }
}
