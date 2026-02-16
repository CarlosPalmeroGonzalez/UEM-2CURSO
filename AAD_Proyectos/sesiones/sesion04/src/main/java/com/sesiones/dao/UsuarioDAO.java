package com.sesiones.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sesiones.model.Usuario;

public interface UsuarioDAO {
    // IMPORTANTE: Se tiene que generar un DAO para cada modelo que exista.
    // Aquí metemos: todos los métodos contra la base de datos del <Usuario>
    // Interfaz -> métodos abstractos
    // El DAO es un conjunto de método, pero no cómo funcionan

    void insertarUsuario (Usuario usuario) throws SQLException;
    int eliminarUsuarios (String correo); // => Aquí me devolvería el número de usuario que elimina (ya que devuelve un int)
    ArrayList <Usuario> listaUSuarios (); // => Este método devuelvbe un ArrayList con los usuarios
    void actualizarUsuario (Usuario usuario, Object [] datos); 
}
