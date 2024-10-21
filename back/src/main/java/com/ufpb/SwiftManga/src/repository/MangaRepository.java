package com.ufpb.SwiftManga.src.repository;

import com.ufpb.SwiftManga.src.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    List<Manga> findByUserId(Long userId);  // Retorna os mangas de um usuário específico
}
