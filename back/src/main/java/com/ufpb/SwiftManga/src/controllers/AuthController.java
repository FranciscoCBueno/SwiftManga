package com.ufpb.SwiftManga.src.controllers;

import com.ufpb.SwiftManga.src.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ufpb.SwiftManga.src.dto.LoginRequestDTO;
import com.ufpb.SwiftManga.src.dto.TokenResponseDTO;
import com.ufpb.SwiftManga.src.dto.ChangePasswordRequestDTO;
import com.ufpb.SwiftManga.src.service.UserService;

@RestController
@RequestMapping(path = "/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public TokenResponseDTO createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new TokenResponseDTO(token);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        try {
            // Verifica e atualiza a senha usando o serviço de usuários
            userService.changePassword(
                    changePasswordRequestDTO.getUserId(),
                    changePasswordRequestDTO.getOldPassword(),
                    changePasswordRequestDTO.getNewPassword()
            );
            return "Senha alterada com sucesso";
        } catch (Exception e) {
            return "Erro ao alterar senha: " + e.getMessage();
        }
    }
}
