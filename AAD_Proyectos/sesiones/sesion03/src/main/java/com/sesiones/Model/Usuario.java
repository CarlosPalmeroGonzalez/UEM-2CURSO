package com.sesiones.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// @Getter Esto son anotaciones y permiten utilizar la librería para declarar los getter y setter de esta clase
// @Setter
@Data // => Esto te da los getter, setter y toString de la clase 
@AllArgsConstructor // => Constructor con todos los atributos
@NoArgsConstructor // => Constructor Vacio

/* La interfaz <Serializable> permite segmentar el objeto en sus atributos para poder guardar la información por partes */

public class Usuario implements Serializable { 
    /* Es Static porque el serial pertenece a la clase, no al objeto */
    private static Long serialVersionUID = 1L; // => Esto es como la etiqueta de como se guarda en el fichero (Es como meterle una referencia)
        /* Todas los atributos de las clases debería iniciar como privadas (Borja no quiere ver variables de clase (atributos) publicas) */
    private int id;
    private String nombre, apellido, correo, dni;
    private int telefono;
    
    // 1 -> Cuando yo escribo un constructor (sin escribir el por constructor vacio), el constructor por defecto desaparece y coge el único que está escrito
    public Usuario (String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    } 

}
