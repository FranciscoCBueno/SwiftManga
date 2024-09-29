package com.ufpb.SwiftManga.src.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ufpb.SwiftManga.src.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufpb.SwiftManga.src.dto.UserDto;
import com.ufpb.SwiftManga.src.model.User;
import com.ufpb.SwiftManga.src.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto createUser(UserDto userDto) {
        return toDto(userRepository.save(toUser(userDto)));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId) {
        return toDto(userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("User not found with id: " + userId)));
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ItemNotFoundException("User not found with id: " + userId));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return userDto;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void changePassword(Long userId, String oldPassword, String newPassword) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Usuário " + userId + " não encontrado"));
        if (!bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new Exception("Senha antiga incorreta");
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public UserDto toDto (User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toUser (UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
