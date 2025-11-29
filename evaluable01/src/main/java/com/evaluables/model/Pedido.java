package com.evaluables.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pedido {
    /* ATRIBUTOS */
    private int id;
    private int id_producto;
    private int id_empleado;
    private String nombre_pedido;
    private int cuantia;
}
