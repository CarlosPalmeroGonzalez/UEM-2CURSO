package com.sesiones.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sesiones.dao.UsuarioDAOImp;
import com.sesiones.model.Usuario;

public class EmpresaController {
    
    // En esta clase se le introduce la lógica que necesito que tenga la aplicación y se llamará al DAO cuando lo necesite 
    private UsuarioDAOImp usuarioDAOImp; // Invocamos a la clase aqui sin instanciarla

    public void darAltaUsuario (Usuario usuario) {
        // Aquí aplico la lógica del negocio
            // Ejemplo:
            // Compruebo si tengo guardadad toda la documentación antes de darle de alta
            // Envio los papeles a la SS para que conste en alta
            // Envio a la gestoria toda la documentacion
            // Lo guardo en base de datos => ahora vamos a hacer este paso
        Scanner sc = new Scanner (System.in);
        String correo = null;
        boolean fallo = false;

        
        usuarioDAOImp = new UsuarioDAOImp(); // => instancias la clase en el método que lo necesitemos
        do { 
            try {
                if (fallo) { // => si fallo es true, entramos por aqui
                    usuario.setCorreo(correo);
                }
                usuarioDAOImp.insertarUsuario(usuario);
                fallo = false; // Si llegas hasta aqui, signifca que no has entrado por el catch
            } catch (SQLException e) {
                System.out.println("Correo duplicado");
                System.out.println("Indica un correo nuevo:");
                correo = sc.next();
                fallo = true;
            }
        } while (fallo == true);
        System.out.println("Usuario insertado correctamente.");
    }
    public void despedirUsuario() {
        
    }
    public void buscarUsuario () {
        // Lógica de negocio de obtencion de los usuarios
        usuarioDAOImp = new UsuarioDAOImp();
        ArrayList <Usuario> lista = usuarioDAOImp.listaUSuarios();
        // Mando un correo a todos los usuarios
        for ( Usuario user : lista) {
            System.out.println(user);
        }
    }
}
