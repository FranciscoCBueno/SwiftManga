package com.ufpb.SwiftManga.src.service;

import com.ufpb.SwiftManga.src.dto.GenreDTO;
import com.ufpb.SwiftManga.src.dto.HQDTO;
import com.ufpb.SwiftManga.src.model.HQ;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.model.Genre;
import com.ufpb.SwiftManga.src.repository.HQRepository;
import com.ufpb.SwiftManga.src.repository.UserRepository;
import com.ufpb.SwiftManga.src.repository.GenreRepository;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class HQService {

    @Autowired
    private HQRepository hqRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenreRepository genreRepository;

    public List<HQDTO> findAllByUserId(Long userId) {
        List<HQ> hqs = hqRepository.findByUserId(userId);
        
        // Inicializa os gêneros explicitamente se o fetch estiver LAZY
        hqs.forEach(hq -> Hibernate.initialize(hq.getGenres()));
        
        return hqs.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<HQDTO> findById(Long id) {
        Optional<HQ> hq = hqRepository.findById(id);
        return hq.map(this::convertToDTO);
    }

    public HQDTO saveHQ(HQDTO hqDTO) {
        HQ hq = convertToEntity(hqDTO);
        hq = hqRepository.save(hq);
        return convertToDTO(hq);
    }

    public void deleteHQ(Long id) {
        hqRepository.deleteById(id);
    }

    public HQDTO convertToDTO(HQ hq) {
        HQDTO hqDTO = new HQDTO();
        hqDTO.setId(hq.getId());
        hqDTO.setTitle(hq.getTitle());
        hqDTO.setArtist(hq.getArtist());
        hqDTO.setPublisher(hq.getPublisher());
        hqDTO.setIssue(hq.getIssue());
        hqDTO.setClassification(hq.getClassification());
        hqDTO.setReleaseDate(hq.getReleaseDate());
        hqDTO.setDescription(hq.getDescription());
        hqDTO.setTags(hq.getTags());
        hqDTO.setLanguage(hq.getLanguage());
        hqDTO.setUserId(hq.getUser().getId());
    
        // Mapeia os gêneros associados como uma string separada por vírgula ou como uma lista, conforme preferir
        String genres = hq.getGenres().stream()
            .map(Genre::getName)
            .collect(Collectors.joining(", "));  // Aqui você junta os nomes dos gêneros separados por vírgula
        hqDTO.setGenres(genres);
    
        return hqDTO;
    }
    
    

    private HQ convertToEntity(HQDTO hqDTO) {
        HQ hq = new HQ();
        if (hqDTO.getId() != null) {
            hq = hqRepository.findById(hqDTO.getId())
                    .orElseThrow(() -> new RuntimeException("HQ não encontrada com ID: " + hqDTO.getId()));
        }
    
        hq.setTitle(hqDTO.getTitle());
        hq.setArtist(hqDTO.getArtist());
        hq.setPublisher(hqDTO.getPublisher());
        hq.setIssue(hqDTO.getIssue());
        hq.setClassification(hqDTO.getClassification());
        hq.setReleaseDate(hqDTO.getReleaseDate());
        hq.setDescription(hqDTO.getDescription());
        hq.setTags(hqDTO.getTags());
        hq.setLanguage(hqDTO.getLanguage());
    
        User user = userRepository.findById(hqDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + hqDTO.getUserId()));
        hq.setUser(user);
    
        // Buscar os gêneros pelos nomes
        if (hqDTO.getGenres() != null && !hqDTO.getGenres().isEmpty()) {
            Set<Genre> genres = Arrays.stream(hqDTO.getGenres().split(","))
                    .map(String::trim)  // Remove espaços em branco ao redor
                    .map(name -> genreRepository.findByName(name)
                            .orElseGet(() -> {
                                Genre newGenre = new Genre();
                                newGenre.setName(name);
                                return genreRepository.save(newGenre);
                            }))
                    .collect(Collectors.toSet());
            hq.setGenres(genres);
        }
    
        return hq;
    }    

    private GenreDTO convertGenreToDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

        // Em HQService
    public Set<HQ> findHQsByUserId(Long userId) {
        return new HashSet<>(hqRepository.findByUserId(userId));
}
}
