package com.codal.erp.controller;

import com.codal.erp.model.Usuario;
import com.codal.erp.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario criar(@RequestBody Usuario user) {
        return service.criarUsuario(user);
    }
}