package com.sesion07.model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esto es que el atributo que vas a poner aqui actua como primary key
    private int id;

    private String nombre; // Si coincide el nombre de la variable con la columna, no hace falta identificar a que columna va

    /* OPTATIVO: RELACIÓN BIDIRECCIONAL */
    // En el mapped se escribe el atributo que hace la relación entre ambas entidades ( en este caso es "perfil" que es un objeto de la clase <Perfil> y que se definió
    // en la clase <Emplead>)
    @OneToMany (mappedBy = "perfil")
    private List <Empleado> listaEmpleado;

    public Perfil(String nombre) {
        this.nombre = nombre;
    }
    //NOTA: Normalmente, la entidad dominante en una relación manytoone / onetomany es aquella que recibe la Foreing Key
}
