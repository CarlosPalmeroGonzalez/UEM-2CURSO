package com.sesiones;

import java.sql.Connection;
import java.sql.SQLException;

import com.sesiones.controller.EmpresaController;
import com.sesiones.database.DBConnection;
import com.sesiones.model.Usuario;

public class Main {
    public static void main(String[] args) {
        EmpresaController empresaController =  new EmpresaController();
        // empresaController.darAltaUsuario(new Usuario ("Carlos", "carlos@gmail.com", 123, 1));ç
        empresaController.buscarUsuario();
    }
}