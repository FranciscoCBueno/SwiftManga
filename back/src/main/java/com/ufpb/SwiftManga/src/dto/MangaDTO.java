package com.ufpb.SwiftManga.src.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

import com.ufpb.SwiftManga.src.enums.Classification;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MangaDTO {
    private Long id;
    private String title;
    private String author;
    private Integer volume;
    private Classification classification;  // Alterar de String para enum Classification
    private LocalDate releaseDate;
    private String description;
    private String tags;
    private String language;
    private Long userId;  // Referência ao ID do usuário
}
