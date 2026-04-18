package com.codal.erp.controller;

import com.codal.erp.dto.LoginRequestDTO;
import com.codal.erp.dto.LoginResponseDTO;
import com.codal.erp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired // 👈 FALTAVA ISSO
    private AuthService service; // 👈 FALTAVA ISSO

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }
}