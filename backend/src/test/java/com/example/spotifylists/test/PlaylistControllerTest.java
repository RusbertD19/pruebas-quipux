package com.example.spotifylists.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.spotifylists.controller.PlaylistController;
import com.example.spotifylists.repository.PlaylistRepository;
import com.example.spotifylists.security.JwtFilter;
import com.example.spotifylists.security.JwtUtil;
import com.example.spotifylists.service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(PlaylistController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")  // Usa un perfil especial sin seguridad
public class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService playlistService;

    @MockBean
    private JwtFilter jwtFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void eliminarPlaylist_PlaylistExiste_Retorna204() throws Exception {
        doNothing().when(playlistService).eliminarPlaylist("123");

        mockMvc.perform(delete("/lists/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarPlaylist_PlaylistNoExiste_Retorna404() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist no encontrada"))
                .when(playlistService).eliminarPlaylist("no-existe");

        mockMvc.perform(delete("/lists/no-existe"))
                .andExpect(status().isNotFound());
    }
}
