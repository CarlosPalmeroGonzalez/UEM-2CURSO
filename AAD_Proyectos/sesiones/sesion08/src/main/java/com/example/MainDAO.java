package com.example;

import com.example.dao.ClienteDAOImp;
import com.example.dao.EmpleadoDAOImp;
import com.example.dao.PerfilDAOImp;

public class MainDAO {
    public static void main(String[] args) {
        EmpleadoDAOImp empleadoDAO = new EmpleadoDAOImp();
        PerfilDAOImp perfilDAO = new PerfilDAOImp();
        ClienteDAOImp clienteDAO = new ClienteDAOImp();
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

        /* == SESION 07 == */
        //empleadoDAO.actualizarUsuario("Carlos", "Tenerife");
        //perfilDAO.getUsuariosPerfil(2);

        /* == SESION 08 ==  */
        clienteDAO.getAllEmpleadosReserva(1);
        empleadoDAO.getAllClientes(1);
    }
}
