package com.example.sesion06;

import com.example.sesion06.dao.EmpleadoDAOImp;

public class MainDAO {
    public static void main(String[] args) {
        EmpleadoDAOImp empleadoDAO = new EmpleadoDAOImp();
        /*Empleado emp = new Empleado("Alba", "Ruano", "alba@gmail.com", 30000, 
            new Direccion("Madrid", "Madrid", "C/ Leganes") ,"Becario");
        empleadoDAO.insertarUsuario(emp);*/

        // empleadoDAO.borrarUsuario(5);
        // empleadoDAO.actualizarUsuario(2, "C/ La Restinga");
        // empleadoDAO.seleccionUSuario(2);
        // empleadoDAO.seleccionAll();
        /*System.out.println("Sacando los empleados de Madrid:");
        empleadoDAO.selectByProvincia("Madrid");
        System.out.println("Sacando los empleados de SC de Tenerife:");
        empleadoDAO.selectByProvincia("SC de Tenerife");*/
        //empleadoDAO.selectByLocalidad("Barcelona");

        empleadoDAO.actualizarUsuario("Carlos", "Tenerife");
    }
}
