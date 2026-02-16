package com.sesion07.dao;

// TODO. Cuidado con la importación. Importante que sea org.hibernate
import org.hibernate.Session;
import org.hibernate.SessionFactory; 
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sesion07.model.Empleado;
import com.sesion07.model.Perfil;

public class PerfilDAOImp {
    
    private SessionFactory sf;
    private Session sn;
    private Transaction tn;

    public void getUsuariosPerfil (int id) {
        // Creo el session factory
        sf = new Configuration().configure().buildSessionFactory();
        sn = sf.openSession();
        tn = sn.beginTransaction();
        Perfil perfil = sn.find(Perfil.class, id);
        for (Empleado e : perfil.getListaEmpleado()) {
            e.mostrarDatos();
        }
        // Como siempre se repite este patrón de abrir y cerrar transacciónes, podriamos crearnos una clase "utils" donde introducir esto y llamarla para evitar escribirla una y otra vez
        tn.commit();
        sn.close();
        sf.close();
    }
}
