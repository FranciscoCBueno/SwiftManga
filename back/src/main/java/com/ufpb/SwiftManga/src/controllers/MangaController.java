package com.ufpb.SwiftManga.src.controllers;

import com.ufpb.SwiftManga.src.dto.MangaDto;
import com.ufpb.SwiftManga.src.service.MangaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class MangaController {
    private final MangaService mangaService;

    public MangaController(MangaService mangaService) {
        this.mangaService = mangaService;
    }

    @GetMapping(path="/manga")
    public List<MangaDto> listMangas() {
        return mangaService.getAllManga();
    }

    @GetMapping(path="/manga/{mangaId}")
    public MangaDto getMangaById(@PathVariable Long mangaId) {
        return mangaService.getMangaById(mangaId);
    }

    @PostMapping(path="/createManga")
    public MangaDto createManga(@RequestBody @Valid MangaDto mangaDto) {
        return mangaService.createManga(mangaDto);
    }

    @PutMapping(path="/updateManga/{mangaId}")
    public MangaDto updateManga(@PathVariable Long mangaId, @RequestBody @Valid MangaDto manga) {
        return mangaService.updateManga(mangaId, manga);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path="deleteManga/{mangaId}")
    public void deleteManga(@PathVariable Long mangaId) {
        mangaService.deleteManga(mangaId);
    }
}
