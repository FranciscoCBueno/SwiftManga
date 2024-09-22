package com.ufpb.SwiftManga.src.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.ufpb.SwiftManga.src.dto.MangaDto;
import com.ufpb.SwiftManga.src.model.Manga;
import com.ufpb.SwiftManga.src.repository.MangaRepository;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    public MangaDto createManga(MangaDto mangaDto) {
        Manga manga = new Manga();
        manga.setTitle(mangaDto.getTitle());
        manga.setAuthor(mangaDto.getAuthor());
        manga.setVolume(mangaDto.getVolume());
        manga.setClassification(mangaDto.getClassification());
        mangaRepository.save(manga);
        return mangaDto; // Conversion to DTO should be implemented
    }

    public List<MangaDto> getAllMangas() {
        return mangaRepository.findAll().stream().map(manga -> {
            MangaDto dto = new MangaDto();
            dto.setId(manga.getId());
            dto.setTitle(manga.getTitle());
            dto.setAuthor(manga.getAuthor());
            dto.setVolume(manga.getVolume());
            dto.setClassification(manga.getClassification());
            return dto;
        }).collect(Collectors.toList());
    }

    public MangaDto getMangaById(Long mangaId) {
        Manga manga = mangaRepository.findById(mangaId)
            .orElseThrow(() -> new ResourceNotFoundException("Manga not found with id: " + mangaId));
        MangaDto dto = new MangaDto();
        dto.setId(manga.getId());
        dto.setTitle(manga.getTitle());
        dto.setAuthor(manga.getAuthor());
        dto.setVolume(manga.getVolume());
        dto.setClassification(manga.getClassification());
        return dto;
    }

    public MangaDto updateManga(Long mangaId, MangaDto mangaDto) {
        Manga manga = mangaRepository.findById(mangaId)
            .orElseThrow(() -> new ResourceNotFoundException("Manga not found with id: " + mangaId));
        manga.setTitle(mangaDto.getTitle());
        manga.setAuthor(mangaDto.getAuthor());
        manga.setVolume(mangaDto.getVolume());
        manga.setClassification(mangaDto.getClassification());
        mangaRepository.save(manga);
        return mangaDto;
    }

    public void deleteManga(Long mangaId) {
        mangaRepository.deleteById(mangaId);
    }
}
