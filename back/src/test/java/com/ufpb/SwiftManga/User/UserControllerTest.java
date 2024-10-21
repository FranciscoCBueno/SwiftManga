package com.ufpb.SwiftManga.User;

import com.ufpb.SwiftManga.src.controllers.UserController;
import com.ufpb.SwiftManga.src.dto.UserDTO;
import com.ufpb.SwiftManga.src.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("newUser");
        userDTO.setEmail("new@example.com");
        userDTO.setPassword("password123");

        when(userService.saveUser(any(UserDTO.class))).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("newUser", response.getBody().getUsername());
        verify(userService, times(1)).saveUser(any(UserDTO.class));
    }

    @Test
    public void testGetUserById() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("testUser");

        when(userService.findById(1L)).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO.getUsername(), response.getBody().getUsername());
        verify(userService, times(1)).findById(1L);
    }

    @Test
    public void testDeleteUser() {
        ResponseEntity<String> response = userController.deleteUser(1L);

        assertEquals("Usuário removido com sucesso.", response.getBody());
        verify(userService, times(1)).deleteUser(1L);
    }
}

