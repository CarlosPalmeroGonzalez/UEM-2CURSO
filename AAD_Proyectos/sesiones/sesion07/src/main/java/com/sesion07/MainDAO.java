package com.sesion07;

import com.sesion07.dao.EmpleadoDAOImp;
import com.sesion07.dao.PerfilDAOImp;

public class MainDAO {
    public static void main(String[] args) {
        EmpleadoDAOImp empleadoDAO = new EmpleadoDAOImp();
        PerfilDAOImp perfilDAO = new PerfilDAOImp();
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

        //empleadoDAO.actualizarUsuario("Carlos", "Tenerife");
        perfilDAO.getUsuariosPerfil(2);
    }
}
