package com.ufpb.SwiftManga.src.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do gênero é obrigatório.")
    @Size(min = 1, max = 100, message = "O nome do gênero deve ter entre 1 e 100 caracteres.")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Manga> mangas;

    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private Set<HQ> hqs;
}
