package com.ufpb.SwiftManga.src.controllers;

import com.ufpb.SwiftManga.src.dto.MangaDTO;
import com.ufpb.SwiftManga.src.service.MangaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mangas")
public class MangaController {

    @Autowired
    private MangaService mangaService;

    @GetMapping("/user/{userId}")
    public List<MangaDTO> getMangasByUserId(@PathVariable Long userId) {
        return mangaService.findAllByUserId(userId);
    }

    @GetMapping("/{mangaId}")
    public Optional<MangaDTO> getMangaById(@PathVariable Long mangaId) {
        return mangaService.findById(mangaId);
    }

    @PostMapping
    public ResponseEntity<MangaDTO> createManga(@RequestBody @Valid MangaDTO mangaDTO) {
        MangaDTO savedManga = mangaService.saveManga(mangaDTO);
        return ResponseEntity.ok(savedManga);
    }
    
    @PutMapping("/{mangaId}")
    public ResponseEntity<MangaDTO> updateManga(@PathVariable Long mangaId, @RequestBody @Valid MangaDTO mangaDTO) {
        mangaDTO.setId(mangaId);  // Garante que o ID será o correto na atualização
        MangaDTO updatedManga = mangaService.saveManga(mangaDTO);
        return ResponseEntity.ok(updatedManga);
    }

    @DeleteMapping("/{mangaId}")
    public String deleteManga(@PathVariable Long mangaId) {
        mangaService.deleteManga(mangaId);
        return "Mangá removido com sucesso.";
    }
}
