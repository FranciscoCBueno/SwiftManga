package com.ufpb.SwiftManga.Manga;

import com.ufpb.SwiftManga.src.controllers.MangaController;
import com.ufpb.SwiftManga.src.dto.MangaDTO;
import com.ufpb.SwiftManga.src.service.MangaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MangaControllerTest {

    @Mock
    private MangaService mangaService;

    @InjectMocks
    private MangaController mangaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMangasByUserId() {
        // Setup
        Long userId = 1L;
        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setId(1L);

        when(mangaService.findAllByUserId(userId)).thenReturn(Arrays.asList(mangaDTO));

        // Execution
        List<MangaDTO> result = mangaController.getMangasByUserId(userId);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(mangaService, times(1)).findAllByUserId(userId);
    }

    @Test
    public void testGetMangaById() {
        // Setup
        Long mangaId = 1L;
        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setId(mangaId);

        when(mangaService.findById(mangaId)).thenReturn(Optional.of(mangaDTO));

        // Execution
        Optional<MangaDTO> result = mangaController.getMangaById(mangaId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(mangaId, result.get().getId());
        verify(mangaService, times(1)).findById(mangaId);
    }

    @Test
    public void testCreateManga() {
        // Setup
        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setTitle("Naruto");

        when(mangaService.saveManga(any(MangaDTO.class))).thenReturn(mangaDTO);

        // Execution
        ResponseEntity<MangaDTO> response = mangaController.createManga(mangaDTO);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mangaDTO, response.getBody());
        verify(mangaService, times(1)).saveManga(any(MangaDTO.class));
    }

    @Test
    public void testUpdateManga() {
        // Setup
        Long mangaId = 1L;
        MangaDTO mangaDTO = new MangaDTO();
        mangaDTO.setTitle("Naruto");

        when(mangaService.saveManga(any(MangaDTO.class))).thenReturn(mangaDTO);

        // Execution
        ResponseEntity<MangaDTO> response = mangaController.updateManga(mangaId, mangaDTO);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mangaDTO, response.getBody());
        verify(mangaService, times(1)).saveManga(any(MangaDTO.class));
    }

    @Test
    public void testDeleteManga() {
        // Setup
        Long mangaId = 1L;

        // Execution
        String result = mangaController.deleteManga(mangaId);

        // Assertions
        assertEquals("Mangá removido com sucesso.", result);
        verify(mangaService, times(1)).deleteManga(mangaId);
    }
}
