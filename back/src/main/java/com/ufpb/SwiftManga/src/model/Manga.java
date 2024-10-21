package com.ufpb.SwiftManga.src.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "mangas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title", "volume", "user_id"})
})
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O título é obrigatório.")
    @Size(min = 1, max = 255, message = "O título deve ter entre 1 e 255 caracteres.")
    private String title;

    @NotNull(message = "O autor é obrigatório.")
    @Size(min = 1, max = 255, message = "O autor deve ter entre 1 e 255 caracteres.")
    private String author;

    @NotNull(message = "O volume é obrigatório.")
    @Positive(message = "O volume deve ser um número positivo.")
    private Integer volume;

    @NotNull(message = "A classificação é obrigatória.")
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
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
    @JoinTable(
        name = "manga_genre",
        joinColumns = @JoinColumn(name = "manga_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    private boolean isDeleted = false;

    public enum Classification {
        POSSUIDO, DESEJADO, LIDO, EMPRESTADO;
    }
}
