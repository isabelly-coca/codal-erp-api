package com.codal.erp.service;

import com.codal.erp.model.Usuario;
import com.codal.erp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(Usuario user) {

        // 🔥 CRIPTOGRAFA A SENHA AQUI
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }
}