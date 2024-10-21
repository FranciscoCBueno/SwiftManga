package com.ufpb.SwiftManga.src.service;

import com.ufpb.SwiftManga.src.dto.HQDTO;
import com.ufpb.SwiftManga.src.dto.MangaDTO;
import com.ufpb.SwiftManga.src.dto.UserDTO;
import com.ufpb.SwiftManga.src.model.Manga;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.repository.MangaRepository;
import com.ufpb.SwiftManga.src.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private MangaService mangaService; 
    // Injeção de MangaService
    @Autowired
    private HQService hqService;

    public Optional<UserDTO> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO);
    }

    public Optional<UserDTO> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(this::convertToDTO);
    }

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 8) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres.");
        }
    
        // Verifica se o usuário já existe
        User user;
        if (userDTO.getId() != null) {
            user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userDTO.getId()));
        } else {
            user = new User();
        }
    
        // Atualiza os dados do usuário
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
    
        // Atualiza a senha apenas se for fornecida uma nova
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);
    
        // Salva o usuário no banco de dados
        user = userRepository.save(user);
        
        return convertToDTO(user); // Retorna o DTO atualizado
    }
    

    public boolean checkPassword(UserDTO userDTO, String rawPassword) {
        // Verifica se a senha fornecida corresponde ao hash armazenado
        return passwordEncoder.matches(rawPassword, userDTO.getPassword());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());
    
        List<MangaDTO> mangas = mangaService.findAllByUserId(user.getId());
        userDTO.setMangas(new HashSet<>(mangas));
        
        List<HQDTO> hqs = hqService.findAllByUserId(user.getId());
        userDTO.setHqs(new HashSet<>(hqs));
    
        return userDTO;
    }
    
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  // A senha já deve estar criptografada
    
        if (userDTO.getMangas() != null && !userDTO.getMangas().isEmpty()) {
            Set<Long> mangaIds = userDTO.getMangas().stream()
                                        .map(MangaDTO::getId)
                                        .collect(Collectors.toSet());
            List<Manga> mangas = mangaRepository.findAllById(mangaIds);
            if (mangas.size() != mangaIds.size()) {
                throw new RuntimeException("One or more Manga not found");
            }
            user.setMangas(new HashSet<>(mangas));
        }
    
        return user;
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new Exception("Usuário não encontrado.");
        }

        User user = userOptional.get();

        // Verifica se a senha antiga corresponde ao hash armazenado
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new Exception("A senha antiga está incorreta.");
        }

        // Atualiza a senha com o novo hash
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
