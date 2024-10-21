package com.ufpb.SwiftManga.HQs;

import com.ufpb.SwiftManga.src.controllers.HQController;
import com.ufpb.SwiftManga.src.dto.HQDTO;
import com.ufpb.SwiftManga.src.service.HQService;
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

public class HQControllerTest {

    @Mock
    private HQService hqService;

    @InjectMocks
    private HQController hqController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHQsByUserId() {
        // Setup
        Long userId = 1L;
        HQDTO hqDTO = new HQDTO();
        hqDTO.setId(1L);

        when(hqService.findAllByUserId(userId)).thenReturn(Arrays.asList(hqDTO));

        // Execution
        List<HQDTO> result = hqController.getHQsByUserId(userId);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(hqService, times(1)).findAllByUserId(userId);
    }

    @Test
    public void testGetHQById() {
        // Setup
        Long hqId = 1L;
        HQDTO hqDTO = new HQDTO();
        hqDTO.setId(hqId);

        when(hqService.findById(hqId)).thenReturn(Optional.of(hqDTO));

        // Execution
        Optional<HQDTO> result = hqController.getHQById(hqId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(hqId, result.get().getId());
        verify(hqService, times(1)).findById(hqId);
    }

    @Test
    public void testCreateHQ() {
        // Setup
        HQDTO hqDTO = new HQDTO();
        hqDTO.setTitle("Batman");

        when(hqService.saveHQ(any(HQDTO.class))).thenReturn(hqDTO);

        // Execution
        ResponseEntity<HQDTO> response = hqController.createHQ(hqDTO);

        // Assertions
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hqDTO, response.getBody());
        verify(hqService, times(1)).saveHQ(any(HQDTO.class));
    }

    @Test
    public void testUpdateHQ() {
        // Setup
        Long hqId = 1L;
        HQDTO hqDTO = new HQDTO();
        hqDTO.setTitle("Batman");

        when(hqService.saveHQ(any(HQDTO.class))).thenReturn(hqDTO);

        // Execution
        ResponseEntity<HQDTO> response = hqController.updateHQ(hqId, hqDTO);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hqDTO, response.getBody());
        verify(hqService, times(1)).saveHQ(any(HQDTO.class));
    }

    @Test
    public void testDeleteHQ() {
        // Setup
        Long hqId = 1L;

        // Execution
        String result = hqController.deleteHQ(hqId);

        // Assertions
        assertEquals("HQ removida com sucesso.", result);
        verify(hqService, times(1)).deleteHQ(hqId);
    }
}
