package com.evaluables.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    /* ATRIBUTOS DE LA CLASE */
    // Utilizamos el static para que el atributo pertenezca exclusivamente a la clase y no al objeto
    private static Connection conexion;

    /* MÉTODOS DE LA CLASE */
    public static Connection obtenerConexion() {
        if (conexion == null) {
            crearConexion();
        }
        return conexion;
    }

    private static void crearConexion() {
        String user = "root";
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/almacen";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.err.println("ERROR al establecer la conexión con la base de datos.");
            System.out.println(e.getMessage());
        }
    }
}
