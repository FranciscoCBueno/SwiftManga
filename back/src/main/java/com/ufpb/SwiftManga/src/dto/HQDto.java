package com.ufpb.SwiftManga.src.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HQDto {
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Artist cannot be blank")
    private String artist;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    @NotNull(message = "Issue cannot be null")
    private Integer issue;

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

    public String getArtist() {
        return artist;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getIssue() {
        return issue;
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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "HQDto [id=" + id + ", title=" + title + ", artist=" + artist + ", publisher=" + publisher + ", issue="
                + issue + ", classification=" + classification + ", userId=" + userId + "]";
    }

    
}
