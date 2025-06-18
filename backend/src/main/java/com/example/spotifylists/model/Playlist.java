package com.example.spotifylists.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    @NotBlank(message = "El nombre no puede estar vacío o ser solo espacios")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ0-9 ]+$", message = "El nombre solo puede contener letras, números y espacios")
    private String nombre;

    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<Cancion> canciones = new ArrayList<>();
}