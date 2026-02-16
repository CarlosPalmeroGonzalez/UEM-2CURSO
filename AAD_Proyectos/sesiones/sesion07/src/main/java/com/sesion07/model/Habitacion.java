package com.sesion07.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "habitaciones")

public class Habitacion {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id_habitacion;
    private int numero;
    private int capacidad;
    private int planta;
    
    public Habitacion(int numero, int capacidad, int planta) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.planta = planta;
    }
    
}