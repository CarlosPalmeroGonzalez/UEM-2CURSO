package com.evaluables.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Empleado {
    /* ATRIBUTOS */
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    
    /* CONSTRUCTORES ESPECÍFICOS */
    public Empleado(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

}
