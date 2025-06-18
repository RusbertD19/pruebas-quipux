package com.example.spotifylists.controller;

import com.example.spotifylists.model.dto.AuthRequest;
import com.example.spotifylists.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        if (("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword()))) {
            String token = jwtUtil.generateToken("admin", "ADMIN");
            return ResponseEntity.ok(Map.of("token", token));
        } else if (("usuario".equals(request.getUsername()) && "clave".equals(request.getPassword()))) {
            String token = jwtUtil.generateToken("usuario", "USER");
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}

