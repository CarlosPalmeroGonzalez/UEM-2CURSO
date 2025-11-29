package com.evaluables.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    /* ATRIBUTOS DE LA CLASE */
    private int id;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precio;
    private boolean fav; // Esta variable la utilizaremos para marcar si es favorita o no. (1 para true y 0 para false)
    
    
    /* CONSTRUCTORES ESPECÍFICOS */
    // Constructor específico para obtener los productos sin la varaible de <fav>
    public Producto(int id, String nombre, String descripcion, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
