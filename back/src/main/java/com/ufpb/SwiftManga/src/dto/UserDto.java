package com.ufpb.swiftmanga.src.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private List<MangaDto> mangas;
    private List<HQDto> hqs;

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MangaDto> getMangas() {
        return mangas;
    }

    public void setMangas(List<MangaDto> mangas) {
        this.mangas = mangas;
    }

    public List<HQDto> getHqs() {
        return hqs;
    }

    public void setHqs(List<HQDto> hqs) {
        this.hqs = hqs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", mangas=" + mangas + ", hqs="
                + hqs + "]";
    }
    
}
