package com.example.spotifylists.service;

import com.example.spotifylists.model.Playlist;
import com.example.spotifylists.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public Playlist crearPlaylist(Playlist playlist) {
        if (playlist.getNombre() == null || playlist.getNombre().isEmpty()) {
            throw new IllegalArgumentException("Nombre no válido");
        }
        return playlistRepository.save(playlist);
    }

    public List<Playlist> obtenerTodas() {
        return playlistRepository.findAll();
    }

    public Playlist obtenerPorNombre(String nombre) {
        return playlistRepository.findById(nombre)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));
    }

        public void eliminarPlaylist(String nombre) {
            if (!playlistRepository.existsById(nombre)) {
                throw new RuntimeException("Playlist no encontrada"); // Esto activará el 404
            }
            playlistRepository.deleteById(nombre);
        }
    }

