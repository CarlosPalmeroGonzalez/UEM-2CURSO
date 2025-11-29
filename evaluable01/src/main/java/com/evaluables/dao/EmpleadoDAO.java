package com.evaluables.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.evaluables.model.Empleado;

public interface EmpleadoDAO {

    void insertarEmpleado (Empleado empleado) throws SQLException;
    ArrayList <Empleado> mostrarEmpleados();
}
