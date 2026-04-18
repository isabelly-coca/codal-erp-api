package com.codal.erp.service;

import com.codal.erp.dto.LoginRequestDTO;
import com.codal.erp.dto.LoginResponseDTO;
import com.codal.erp.model.Usuario;
import com.codal.erp.repository.UsuarioRepository;
import com.codal.erp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder; // 🔥 FALTOU ISSO

    public LoginResponseDTO login(LoginRequestDTO dto) {

        Usuario user = repository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtil.gerarToken(user.getUsername());

        return new LoginResponseDTO(token);
    }
}
