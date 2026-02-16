package com.sesiones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    private int id;
    private String nombre, correo;
    private int telefono;
    private int idPerfil;
    
    // Si hay un identificador que no me interesa, con el constructor seleccionamos los que utilizaremos
    // En este caso ID se autorellena , por lo que no nos interesa recogerlo
    public Usuario(String nombre, String correo, int telefono, int idPerfil) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }
}
