package com.example.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

// TODO. Mucho cuidado con el toString() => Puede crear un bucle que haga que nuestro programa se pete
@Data  // genera getter, setter, toSting y el hash (que genera el código de cada uno de los objetos)
// Para evitar problemas con el DATA, vamos a excluir el hashset de clientes para evitar concurrencias de informacion
@ToString(exclude = "clientes" ) // => Excluye toSting del hashset "clientes"
@EqualsAndHashCode(exclude = "clientes") // => Excluye los equasl y hash code del hashset "clientes"
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
    @Embedded 
    private Direccion direccion;
    @Transient
    private String categoria;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_perfil")
    private Perfil perfil; 

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "direccion")
    private Direccion direccionRel;

    @ManyToMany (mappedBy = "empleados") // Mapeado por <empleados> que es nuestro hashSet de la clase clientes 
    private Set <Cliente> clientes = new HashSet<>();




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
