package com.ufpb.SwiftManga.src.dto;

import java.util.Objects;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class UserDTOResponse {
    private Long id;
    private String nome;
    private String email;
    private String username;
    private Set<MangaDTO> mangas;
    private Set<HQDTO> hqs;

    // getters and setters for new fields
    public Set<MangaDTO> getMangas() {
        return mangas;
    }

    public void setMangas(Set<MangaDTO> mangas) {
        this.mangas = mangas;
    }

    public Set<HQDTO> getHqs() {
        return hqs;
    }

    public void setHqs(Set<HQDTO> hqs) {
        this.hqs = hqs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTOResponse that = (UserDTOResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, username);
    }

    @Override
    public String toString() {
        return "UserDTOResponse{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
