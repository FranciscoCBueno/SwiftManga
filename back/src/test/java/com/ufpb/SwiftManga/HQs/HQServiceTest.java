package com.ufpb.SwiftManga.HQs;

import com.ufpb.SwiftManga.src.dto.HQDTO;
import com.ufpb.SwiftManga.src.model.HQ;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.repository.HQRepository;
import com.ufpb.SwiftManga.src.repository.UserRepository;
import com.ufpb.SwiftManga.src.service.HQService;
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

public class HQServiceTest {

    @Mock
    private HQRepository hqRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private HQService hqService;

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

        HQ hq = new HQ();
        hq.setId(1L);
        hq.setUser(user);

        when(hqRepository.findByUserId(userId)).thenReturn(Arrays.asList(hq));

        // Execution
        List<HQDTO> result = hqService.findAllByUserId(userId);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(hq.getId(), result.get(0).getId());
        verify(hqRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testFindById() {
        // Setup
        Long hqId = 1L;
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        HQ hq = new HQ();
        hq.setId(hqId);
        hq.setUser(user);  // Certifique-se de que o user não seja nulo

        when(hqRepository.findById(hqId)).thenReturn(Optional.of(hq));

        // Execution
        Optional<HQDTO> result = hqService.findById(hqId);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(hqId, result.get().getId());
        assertEquals(userId, result.get().getUserId()); // Verifica se o userId foi retornado corretamente
        verify(hqRepository, times(1)).findById(hqId);
    }

    @Test
    public void testSaveHQ() {
        // Setup
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        HQDTO hqDTO = new HQDTO();
        hqDTO.setUserId(userId);
        hqDTO.setTitle("Batman");

        HQ hq = new HQ();
        hq.setId(1L);
        hq.setUser(user);
        hq.setTitle("Batman");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(hqRepository.save(any(HQ.class))).thenReturn(hq);

        // Execution
        HQDTO savedHQ = hqService.saveHQ(hqDTO);

        // Assertions
        assertNotNull(savedHQ);
        assertEquals("Batman", savedHQ.getTitle());
        verify(hqRepository, times(1)).save(any(HQ.class));
    }

    @Test
    public void testDeleteHQ() {
        // Setup
        Long hqId = 1L;

        // Execution
        hqService.deleteHQ(hqId);

        // Verifica se o método deleteById foi chamado uma vez
        verify(hqRepository, times(1)).deleteById(hqId);
    }
}

