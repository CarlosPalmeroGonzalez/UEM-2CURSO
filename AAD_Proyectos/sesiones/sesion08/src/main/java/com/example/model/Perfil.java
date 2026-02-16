package com.example.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO cuidado con el string y con el hash
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "perfiles")

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany (mappedBy = "perfil")
    private List <Empleado> listaEmpleado;

    public Perfil(String nombre) {
        this.nombre = nombre;
    }
}
