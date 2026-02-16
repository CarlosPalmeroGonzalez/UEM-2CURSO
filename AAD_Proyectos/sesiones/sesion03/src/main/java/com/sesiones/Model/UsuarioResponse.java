package com.sesiones.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties (ignoreUnknown=true)
// Si no deseamos algunas de los datos del Json, tenemos que indicarle que los datos desconocidos los ignore

public class UsuarioResponse {

    private List<UsuarioJSON> users;
}
