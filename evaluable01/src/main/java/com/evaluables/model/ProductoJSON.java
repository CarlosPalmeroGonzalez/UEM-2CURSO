package com.evaluables.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getters y Setters de los atributos de la clase
@AllArgsConstructor // Constructor con todos los atributos
@NoArgsConstructor // Constructor vacio

@JsonIgnoreProperties (ignoreUnknown=true) // Ignoramos aquellos datos de la base de datos JSON que no se recogen

public class ProductoJSON {
    private int id;
    private String title;
    private String description;
    private int stock;
    private double price;

    /* 
    No realizamos ningún constructor específico ya que vamos a utilizar el constructor con todos los atributos.
    Al analizar la base de datos JSON de productos he visto que cada producto ya tiene un id fijado y por lo tanto utilizaremos el que ya han establecido.
    */
}
