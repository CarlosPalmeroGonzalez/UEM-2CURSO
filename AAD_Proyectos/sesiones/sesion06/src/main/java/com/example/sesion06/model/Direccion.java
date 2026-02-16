package com.example.sesion06.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

//Lo que queremos es que esta clase forme parte de la clase <Empleado>
@Embeddable

public class Direccion {
    private String localidad;
    private String provincia;
    private String calle;
}
