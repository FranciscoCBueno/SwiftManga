package com.ufpb.SwiftManga.Manga;

import com.ufpb.SwiftManga.src.dto.MangaDTO;
import com.ufpb.SwiftManga.src.model.Manga;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.repository.MangaRepository;
import com.ufpb.SwiftManga.src.repository.UserRepository;
import com.ufpb.SwiftManga.src.service.MangaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MangaServiceTest {

    @Mock
    private MangaRepository mangaRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MangaService mangaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllByUserId() {
        // Setup
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        Manga manga = new Manga();
        manga.setId(1L);
        manga.setUser(user);

        when(mangaRepository.findByUserId(userId)).thenReturn(Arrays.asList(manga));

        // Execution
        List<MangaDTO> result = mangaService.findAllByUserId(userId);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(manga.getId(), result.get(0).getId());
        verify(mangaRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testFindById() {
        // Setup
        Long mangaId = 1L;
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        Manga manga = new Manga();
        manga.setId(mangaId);
        manga.setUser(user);  // Certifique-se de que o user não seja nulo

        when(mangaRepository.findById(mangaId)).thenReturn(Optional.of(manga));

        // Execution
        Optional<MangaDTO> result = mangaService.findById(mangaId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(mangaId, result.get().getId());
        assertEquals(userId, result.get().getUserId()); // Verifica se o userId foi retornado corretamente
        verify(mangaRepository, times(1)).findById(mangaId);
    }

    @Test
    public void testSaveManga() {
        // Setup
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setUserId(userId);
        mangaDTO.setTitle("Naruto");

        Manga manga = new Manga();
        manga.setId(1L);
        manga.setUser(user);
        manga.setTitle("Naruto");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mangaRepository.save(any(Manga.class))).thenReturn(manga);

        // Execution
        MangaDTO savedManga = mangaService.saveManga(mangaDTO);

        // Assertions
        assertNotNull(savedManga);
        assertEquals("Naruto", savedManga.getTitle());
        verify(mangaRepository, times(1)).save(any(Manga.class));
    }

    @Test
    public void testDeleteManga() {
        // Setup
        Long mangaId = 1L;

        // Execution
        mangaService.deleteManga(mangaId);

        // Verifica se o método deleteById foi chamado uma vez
        verify(mangaRepository, times(1)).deleteById(mangaId);
    }
}

