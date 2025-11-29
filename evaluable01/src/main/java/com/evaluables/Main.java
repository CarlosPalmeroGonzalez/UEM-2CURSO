package com.evaluables;

//import java.sql.Connection;
//import java.sql.SQLException;

import com.evaluables.controller.AlmacenController;
//import com.evaluables.database.DBConnection;
import com.evaluables.message.Mensajes;

public class Main {
    public static void main(String[] args) {
        // Declaración de clases y objetos 
        AlmacenController ac = new AlmacenController();
        Mensajes mj = new Mensajes();
        
        // Mensaje de bienvenida
        mj.bienvenida();
        // Accedemos al controlador lógico del almacen
        ac.almacenControllerGestion();
        // Mensaje de despedida
        mj.despedida(); 
    }
}
