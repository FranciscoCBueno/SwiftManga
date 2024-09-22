package com.ufpb.SwiftManga.src.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MangaDto {
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Author cannot be blank")
    private String author;

    @NotNull(message = "Volume cannot be null")
    private Integer volume;

    @NotBlank(message = "Classification cannot be blank")
    private String classification;

    private Long userId;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getClassification() {
        return classification;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MangaDto [id=" + id + ", title=" + title + ", author=" + author + ", volume=" + volume
                + ", classification=" + classification + ", userId=" + userId + "]";
    }

    
}
