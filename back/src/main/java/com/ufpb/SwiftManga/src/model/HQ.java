package com.ufpb.SwiftManga.src.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "hqs", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title", "issue", "user_id"})
})
public class HQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O título é obrigatório.")
    @Size(min = 1, max = 255, message = "O título deve ter entre 1 e 255 caracteres.")
    private String title;

    @NotNull(message = "O artista é obrigatório.")
    @Size(min = 1, max = 255, message = "O artista deve ter entre 1 e 255 caracteres.")
    private String artist;

    @NotNull(message = "A editora é obrigatória.")
    @Size(min = 1, max = 255, message = "A editora deve ter entre 1 e 255 caracteres.")
    private String publisher;

    @NotNull(message = "A edição é obrigatória.")
    @Positive(message = "A edição deve ser um número positivo.")
    private Integer issue;

    @NotNull(message = "A classificação é obrigatória.")
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference // Para evitar recursão infinita
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull(message = "A descrição é obrigatória.")
    private String description;

    @NotNull(message = "As tags são obrigatórias.")
    private String tags;

    @NotNull(message = "O idioma é obrigatório.")
    private String language;

    @NotNull(message = "A data de lançamento é obrigatória.")
    private LocalDate releaseDate;

    @ManyToMany
    @JoinTable(name = "hq_genre", joinColumns = @JoinColumn(name = "hq_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    private boolean isDeleted = false;

    public enum Classification {
        POSSUIDO, DESEJADO, LIDO, EMPRESTADO;
    }
}
