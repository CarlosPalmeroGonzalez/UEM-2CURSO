package com.example.sesion05.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO. Mucho cuidado con el toString() => Puede crear un bucle que haga que nuestro programa se pete
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "empleados")

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    @Column(name = "correo", unique= true)
    private String mail;
    private int salario;
    @Transient
    private String categoria;

    public Empleado(String nombre, String apellido, String mail, int salario, String categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.salario = salario;
        this.categoria = categoria;
    }
}
