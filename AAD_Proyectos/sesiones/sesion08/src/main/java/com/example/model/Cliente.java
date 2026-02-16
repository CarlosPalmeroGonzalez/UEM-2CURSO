package com.example.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

// TODO: CUIDADO CON EL TOSTRING() -> en springboot es @JSONBack o @JSONManager o @JSONIgnore
@Data
@ToString(exclude = "empleados")
@EqualsAndHashCode(exclude = "empleados")
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "clientes")

public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column(name = "email")
    private String correo;

    /* ===== RELACIÓN UNIDIRECCIONAL ====== */
    // Decidimos que esta tabla "Clientes" sea nuestra tabla dominante para la relación 
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // En vez de hacer Join a una tabla, se hace a una columna por ser relación ManyToMany
    // Voy a tener que decir las columnas que tienen la relación (joincolumn es nuestra relación dominante y la inverse es la otra)
    @JoinTable(name = "reservas", 
        joinColumns = @JoinColumn (name="id_cliente"), 
        inverseJoinColumns = @JoinColumn(name = "id_empleado"))
    private Set<Empleado> empleados = new HashSet<>(); // Aquí se va a obtener la "lista" de empleados 


    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public void addEmpleado (Empleado emp) {
        empleados.add(emp); // metodo para añadir el empleado a mi hashset de empleados creado previamente
        emp.getClientes().add(this); // Aqui le estamos añadiendo el clientes de esta clase con "this" a nuestro hashset de empleados para evitar duplicar métodos
    }

    public void mostrarDatos() {
        System.out.println("nombre = " + nombre);
        System.out.println("correo = " + correo);
        System.out.println("nº empleados = " + empleados.size());
    }
}
