package com.evaluables.dao;

import com.evaluables.model.Empleado;
import com.evaluables.model.Producto;

public interface PedidoDAO {

    boolean realizarPedido(Producto producto, Empleado empleado, int cuantia, String nombrePedido);
}
