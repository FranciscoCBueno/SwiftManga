package com.ufpb.SwiftManga.User;

import com.ufpb.SwiftManga.src.dto.UserDTO;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.repository.UserRepository;
import com.ufpb.SwiftManga.src.service.HQService;
import com.ufpb.SwiftManga.src.service.MangaService;
import com.ufpb.SwiftManga.src.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private MangaService mangaService;  // Mock do MangaService

    @Mock
    private HQService hqService;  // Mock do HQService

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        // Dados de entrada
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password123");

        // Dados simulados
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername(userDTO.getUsername());
        savedUser.setEmail(userDTO.getEmail());

        // Configurando os mocks
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(mangaService.findAllByUserId(anyLong())).thenReturn(Collections.emptyList()); // Retorno vazio para o mock
        when(hqService.findAllByUserId(anyLong())).thenReturn(Collections.emptyList()); // Retorno vazio para o mock

        // Execução do teste
        UserDTO createdUser = userService.saveUser(userDTO);

        // Verificações
        assertNotNull(createdUser);
        assertEquals(savedUser.getUsername(), createdUser.getUsername());
        assertEquals(savedUser.getEmail(), createdUser.getEmail());

        // Verifica se o método save foi chamado uma vez
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserDTO> foundUser = userService.findById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("testUser", foundUser.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testChangePassword() throws Exception {
        User user = new User();
        user.setId(1L);

        // Hash da senha antiga
        String oldPasswordHash = passwordEncoder.encode("oldPassword");
        user.setPassword(oldPasswordHash);

        // Simula a busca do usuário no repositório
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Simula a verificação da senha antiga
        when(passwordEncoder.matches("oldPassword", user.getPassword())).thenReturn(true);

        // Nova senha
        String newPassword = "newPassword";

        // Chama o método para alterar a senha
        userService.changePassword(1L, "oldPassword", newPassword);

        // Verifica se a nova senha foi codificada corretamente e salva no usuário
        assertTrue(passwordEncoder.matches(newPassword, user.getPassword()), "A nova senha não corresponde ao hash armazenado.");

        // Verifica se o método save foi chamado com o usuário atualizado
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("updatedUser");
        userDTO.setEmail("updated@example.com");
        userDTO.setPassword("newPassword");

        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("oldUser");
        existingUser.setEmail("old@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("hashedNewPassword");
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserDTO updatedUser = userService.saveUser(userDTO);

        assertNotNull(updatedUser);
        assertEquals(userDTO.getUsername(), updatedUser.getUsername());
        assertEquals(userDTO.getEmail(), updatedUser.getEmail());
        verify(userRepository, times(1)).save(existingUser);
    }

}
