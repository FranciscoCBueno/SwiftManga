package com.ufpb.SwiftManga.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpb.SwiftManga.src.model.Manga;

import java.util.List;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {
    // List<Manga> findByUserId(Long userId);
}
