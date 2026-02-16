package com.sesiones.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)

public class UsuarioJSON {
    // Aqui creamos tantas caracteristicas como la clase que tiene el JSON (solo la que queramos)
    private int id;
    private String firstName, lastName, email;
}
