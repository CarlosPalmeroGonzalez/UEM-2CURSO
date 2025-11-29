package com.evaluables.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties (ignoreUnknown=true)
public class ProductoResponse {
    // Creamos una lista que recogera los objetos de ProductoJSON
    private List <ProductoJSON> products; // Debe tener el mismo nombre que en la lista JSON
}
