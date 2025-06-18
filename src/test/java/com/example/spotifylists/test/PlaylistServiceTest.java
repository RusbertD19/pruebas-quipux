package com.example.spotifylists.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.spotifylists.repository.PlaylistRepository;
import com.example.spotifylists.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {

    @Mock
    private PlaylistRepository playlistRepository;

    @InjectMocks
    private PlaylistService playlistService;

    @Test
    public void eliminarPlaylist_PlaylistExiste_EliminaCorrectamente() {
        // Configura el mock
        when(playlistRepository.existsById("123")).thenReturn(true);

        // Ejecuta el método
        playlistService.eliminarPlaylist("123");

        // Verifica que se llamó a deleteById
        verify(playlistRepository, times(1)).deleteById("123");
    }

    @Test
    public void eliminarPlaylist_PlaylistNoExiste_LanzaExcepcion404() {
        when(playlistRepository.existsById("no-existe")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            playlistService.eliminarPlaylist("no-existe");
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Playlist 'no-existe' no existe", exception.getReason());
    }
}