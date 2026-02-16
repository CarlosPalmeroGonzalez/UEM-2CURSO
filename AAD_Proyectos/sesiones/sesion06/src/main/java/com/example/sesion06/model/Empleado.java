package com.example.sesion06.model;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
@NamedQuery (name = "Empleado.findByLocalidad", query = "FROM Empleado e WHERE e.direccion.localidad=:localidadArgs") // Esto queda compilado una vez,
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
    @Embedded // Atributos que están embebidos y le metemos nuestro objeto de la clase Direccion
    private Direccion direccion;
    @Transient
    private String categoria;

    public Empleado(String nombre, String apellido, String mail, int salario, Direccion direccion, String categoria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.salario = salario;
        this.direccion = direccion;
        this.categoria = categoria;
    }

    public void mostrarDatos() {
        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("apellido = " + apellido);
        System.out.println("correo = " + mail);
        System.out.println("salario = " + salario);
        System.out.println("provincia = " + direccion.getProvincia());
        System.out.println("localidad = " + direccion.getLocalidad());
        System.out.println("calle = " + direccion.getCalle());
    }
}
