package com.ufpb.SwiftManga.src.service;

import com.ufpb.SwiftManga.src.dto.HQDTO;
import com.ufpb.SwiftManga.src.dto.MangaDTO;
import com.ufpb.SwiftManga.src.model.HQ;
import com.ufpb.SwiftManga.src.model.Manga;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.repository.MangaRepository;
import com.ufpb.SwiftManga.src.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MangaService {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private UserRepository userRepository; // Injeção do UserRepository

    public List<MangaDTO> findAllByUserId(Long userId) {
        List<Manga> mangas = mangaRepository.findByUserId(userId);
        return mangas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<MangaDTO> findById(Long id) {
        Optional<Manga> manga = mangaRepository.findById(id);
        return manga.map(this::convertToDTO);
    }

    public MangaDTO saveManga(MangaDTO mangaDTO) {
        Manga manga = convertToEntity(mangaDTO);
        manga = mangaRepository.save(manga);
        return convertToDTO(manga);
    }

    public void deleteManga(Long id) {
        mangaRepository.deleteById(id);
    }

// Em MangaService
public MangaDTO convertToDTO(Manga manga) {
    MangaDTO mangaDTO = new MangaDTO();
    mangaDTO.setId(manga.getId());
    mangaDTO.setTitle(manga.getTitle());
    mangaDTO.setAuthor(manga.getAuthor());
    mangaDTO.setVolume(manga.getVolume());
    mangaDTO.setClassification(manga.getClassification());
    mangaDTO.setDescription(manga.getDescription());
    mangaDTO.setTags(manga.getTags());
    mangaDTO.setLanguage(manga.getLanguage());
    mangaDTO.setReleaseDate(manga.getReleaseDate());
    mangaDTO.setUserId(manga.getUser().getId());
    return mangaDTO;
}

    public HQDTO convertToDTO(HQ hq) {
        HQDTO hqDTO = new HQDTO();
        hqDTO.setId(hq.getId());
        hqDTO.setTitle(hq.getTitle());
        // Inclua outros campos conforme necessário
        return hqDTO;
    }

    private Manga convertToEntity(MangaDTO mangaDTO) {
        Manga manga = new Manga();
        if (mangaDTO.getId() != null) {
            manga.setId(mangaDTO.getId()); // Define o ID apenas se já existir (caso de atualização)
        }
        manga.setTitle(mangaDTO.getTitle());
        manga.setAuthor(mangaDTO.getAuthor());
        manga.setVolume(mangaDTO.getVolume());
        manga.setClassification(mangaDTO.getClassification());
        manga.setDescription(mangaDTO.getDescription());   // Incluindo a descrição
        manga.setTags(mangaDTO.getTags());                 // Incluindo as tags
        manga.setLanguage(mangaDTO.getLanguage());         // Incluindo o idioma
        manga.setReleaseDate(mangaDTO.getReleaseDate());   // Incluindo a data de lançamento
        // Buscar e configurar o usuário associado ao Manga
        User user = userRepository.findById(mangaDTO.getUserId())
                                  .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        manga.setUser(user);
    
        return manga;
    }
}
