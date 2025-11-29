package com.evaluables.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.evaluables.database.DBConnection;
import com.evaluables.database.SchemeDB;
import com.evaluables.model.Empleado;
import com.evaluables.model.Producto;

public class PedidoDAOImp implements PedidoDAO{
    /* ATRIBUTOS */
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rst;
    private Empleado emp;
    private Producto pro;
    

    /* CONSTRUCTORES ESPECÍFICOS */
    public PedidoDAOImp() {
        con = DBConnection.obtenerConexion();
    }

    /* MÉTODOS IMPLEMENTADOS */
    @Override
    public boolean realizarPedido(Producto producto, Empleado empleado, int cuantia, String nombrePedido) {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)"
        , SchemeDB.TAB_PD
        , SchemeDB.TAB_PD_COL_IDPRODUCTO // ID del producto 
        , SchemeDB.TAB_PD_COL_IDEMPLEADO // ID del empleado
        , SchemeDB.TAB_PD_COL_NOMBRE // Nombre del pedido
        , SchemeDB.TAB_PD_COL_CUANTIA); // Unidades de productos
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, producto.getId());
            pst.setInt(2,empleado.getId());
            pst.setString(3, nombrePedido);
            pst.setInt(4, cuantia);
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("ERROR al realizar PreparedStatement");
        }
        return false;
    }

}
