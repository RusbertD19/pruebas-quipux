package com.example.spotifylists.test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.spotifylists.model.Playlist;
import com.example.spotifylists.repository.PlaylistRepository;
import com.example.spotifylists.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Usa una BD H2 en memoria para pruebas
public class PlaylistIntegrationTest {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistService playlistService;

    @Test
    public void eliminarPlaylist_PlaylistExiste_EliminaDeBD() {
        // Crea la playlist con constructor + setters
        Playlist playlist = new Playlist();
        playlist.setNombre("123");
        playlist.setDescripcion("Test");

        playlistRepository.save(playlist);
        playlistService.eliminarPlaylist("123");

        assertFalse(playlistRepository.existsById("123"));
    }
}