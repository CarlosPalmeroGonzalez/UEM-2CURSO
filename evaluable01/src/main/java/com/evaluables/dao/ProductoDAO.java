package com.evaluables.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.evaluables.model.Producto;

public interface ProductoDAO {

    void insertarProductos() throws SQLException;
    ArrayList <Producto> listaProductos ();
    ArrayList <Producto> listaProductosFav ();;
}
