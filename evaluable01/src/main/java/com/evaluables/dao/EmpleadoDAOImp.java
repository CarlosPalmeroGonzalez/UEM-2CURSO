package com.evaluables.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.evaluables.database.DBConnection;
import com.evaluables.database.SchemeDB;
import com.evaluables.model.Empleado;

public class EmpleadoDAOImp implements EmpleadoDAO {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rst;

    public EmpleadoDAOImp() {
        con = DBConnection.obtenerConexion();
    }

    @Override
    public void insertarEmpleado(Empleado empleado) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)"
        , SchemeDB.TAB_EM
        , SchemeDB.TAB_EM_COL_NOMBRE
        , SchemeDB.TAB_EM_COL_APELLIDO
        , SchemeDB.TAB_EM_COL_CORREO);
        pst = con.prepareStatement(query);
        pst.setString(1,empleado.getNombre());
        pst.setString(2,empleado.getApellido());
        pst.setString(3,empleado.getCorreo());
        pst.execute();
    }

    @Override
    public ArrayList <Empleado> mostrarEmpleados() {
        String query = "SELECT * FROM " + SchemeDB.TAB_EM;
        ArrayList <Empleado> listaEmpleados = new ArrayList <>();
        try {
            pst = con.prepareStatement(query);
            rst = pst.executeQuery();
            while (rst.next()) { 
                int id = rst.getInt(SchemeDB.TAB_EM_COL_ID);
                String nombre = rst.getString(SchemeDB.TAB_EM_COL_NOMBRE);
                String apellido = rst.getString(SchemeDB.TAB_EM_COL_APELLIDO);
                String correo = rst.getString(SchemeDB.TAB_EM_COL_CORREO);
                Empleado emp = new Empleado (id, nombre, apellido, correo);
                listaEmpleados.add (emp);
            }
            return listaEmpleados;
        } catch (SQLException e) {
            System.err.println("ERROR al realizar ResultSet");
        }
        return null;
    }

}
