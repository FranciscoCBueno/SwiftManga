package com.ufpb.SwiftManga.src.dto;

import com.ufpb.SwiftManga.src.enums.Classification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class HQDTO {
    private Long id;
    private String title;
    private String artist;
    private String publisher;
    private Integer issue;
    private Classification classification;
    private LocalDate releaseDate;
    private String description;
    private String tags;
    private String language;
    private Long userId;
//    private String genres; // Mude de Set<Long> para uma String que conterá os nomes dos gêneros
}


