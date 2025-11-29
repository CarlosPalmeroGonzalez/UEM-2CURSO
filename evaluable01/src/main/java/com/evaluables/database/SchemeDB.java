package com.evaluables.database;

public interface SchemeDB {
    
    // TABLA DE PRODUCTOS
    String TAB_PR = "productos";
    String TAB_PR_COL_ID = "id";
    String TAB_PR_COL_NOMBRE = "nombre";
    String TAB_PR_COL_DESCRIPCION = "descripcion";
    String TAB_PR_COL_CANTIDAD = "cantidad";
    String TAB_PR_COL_PRECIO = "precio";

    // TABLA DE PRODUCTOS FAVORITOS 
    String TAB_PF = "productos_fav";
    String TAB_PF_COL_ID = "id"; // Esta columna designa si es favorito o no (1 true y 0 false)
    String TAB_PF_COL_IDPRODUCTO = "id_producto"; // Esta trae el ID de la tabla productos

    // TABLA DE EMPLEADOS
    String TAB_EM = "empleados";
    String TAB_EM_COL_ID = "id";
    String TAB_EM_COL_NOMBRE = "nombre";
    String TAB_EM_COL_APELLIDO = "apellido";
    String TAB_EM_COL_CORREO = "correo";

    // TABLA DE PEDIDOS
    String TAB_PD = "pedidos";
    String TAB_PD_COL_ID = "id";
    String TAB_PD_COL_IDPRODUCTO = "id_producto";
    String TAB_PD_COL_IDEMPLEADO ="id_empleado";
    String TAB_PD_COL_NOMBRE = "nombre_pedido";
    String TAB_PD_COL_CUANTIA = "cuantia";
}
