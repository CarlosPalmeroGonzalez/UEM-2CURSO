package com.sesiones.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // static  => pertenece a la clase, no al objeto de la clase. Con esto consigo no saturar las conexiones.
    // Que algo sea <static> quiere decir que tienes disponible un recurso pero a través de la clase, no del objecto
    private static Connection connection;

    // Siempre que tenemos atributos privado y queremos acceder a él => necesitamos un método publico (como los getter y setter) 
    public static Connection getConnection() {
        // Cuando me piden la conexion en el main, si es nulo, llamamos al metodo <createConnection()>
        if (connection == null) {
            createConnection(); // Solo se creará una vez (cuando es nulo), el resto de veces la retorno, así garantizamos que se cree una sola vez
        }
        return connection;
    }

    // Aquí vamos a crear la conexión
    private static void createConnection() {
        // Estos datos podrían ir en un fichero de propiedades para que estuvieran protegidos 
        String user = "root"; // Cuando levantamos XAMPP con el docker, las credenciales por defecto son: root y root
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/AAC_sesion04_reservasUE";
        try {
            connection = DriverManager.getConnection(url, user, pass); // Podemos recoger el fallo ahora o thorwearlo al lugar donde se realice la conexion
        } catch (SQLException e) {
            System.out.println("Fallo en conexión.");
            // System.out.println(e.getMessage()); //=> Con esto podemos ver donde podría ser el error
        }
    }
}
