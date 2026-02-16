package com.sesion07.model;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @Embedded 
    private Direccion direccion;
    @Transient
    private String categoria;
    /* RELACIÓN UNIDIRECCIONAL */
    // fetch => es como vas a recopilar los datos
    // Existen dos grandes tipos de fetch: EAGER y LAZY. | EAGER -> da los datos sin consultar otros. | LAZY -> da los datos habiendo consultado todos los demas
    // TREMENDAMENTE RECOMENDABLE utilizar LAZY
    // cascade =>  significa: qué quieres que pase automáticamente con la otra entidad cuando haces algo con esta
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // Siempre hay que definir (al menos una vez) cual la columna que relaciona
    @JoinColumn(name = "id_perfil")
    private Perfil perfil; 

    /* Las relaciones onetoone son exactamente iguales a las de onetomany */
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn (name = "direccion")
    private Direccion direccionRel;

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
