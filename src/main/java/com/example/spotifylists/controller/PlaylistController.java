package com.example.spotifylists.controller;

import com.example.spotifylists.model.Playlist;
import com.example.spotifylists.service.PlaylistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping
    public ResponseEntity<?> crearPlaylist(@Valid @RequestBody Playlist playlist, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            Playlist nueva = playlistService.crearPlaylist(playlist);
            return ResponseEntity.created(URI.create("/lists/" + nueva.getNombre()))
                    .body(nueva);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        }
    }

    @GetMapping
    public List<Playlist> obtenerTodas() {
        return playlistService.obtenerTodas();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerPorNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(playlistService.obtenerPorNombre(nombre));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> eliminarPlaylist(@PathVariable String nombre) {
        try {
            playlistService.eliminarPlaylist(nombre);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}