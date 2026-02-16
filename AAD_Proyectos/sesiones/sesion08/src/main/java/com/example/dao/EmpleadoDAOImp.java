package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.model.Cliente;
import com.example.model.Empleado;

public class EmpleadoDAOImp {
        private Session sn;
    private Transaction tn;

    public void insertarUsuario (Empleado emp) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        sn.persist(emp); 
        tn.commit(); 
        sn.close(); 
    }

    // Borrar por el ID
    public void borrarUsuario(int id) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Empleado emp = sn.find(Empleado.class, id);
        if (emp != null) { 
            sn.remove(emp);
            System.out.println("Empleado borrado correctamente");
        }
        else {
            System.out.println("No se puede borrar, el empleado no se encuentra");
        }        
        tn.commit(); 
        sn.close(); 
    }

    // Actualización por el ID
    public void actualizarUsuario (int id, String calleNueva) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Empleado emp = sn.find(Empleado.class, id);
        
        if (emp != null) {
            emp.getDireccion().setCalle(calleNueva);
            System.out.println("Empleado actualizado correctamente");
        }
        else {
            System.out.println("No se pudo actualizar, el empleado no se encuentra");
        }        
        tn.commit(); 
        sn.close();
    }

    // Seleccion por el ID
    public void seleccionUSuario (int id) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Empleado emp = sn.find(Empleado.class, id);
        emp.mostrarDatos();      
        tn.commit(); 
        sn.close();
    }

    // Seleccion sin usar el ID
    public void seleccionAll() {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Query <Empleado> query = sn.createQuery("FROM Empleado",Empleado.class);
        List <Empleado> listaResultante = query.getResultList();
        for (Empleado e : listaResultante) {
            e.mostrarDatos();
        }
        tn.commit(); 
        sn.close();
    }

    // Clausula WHERE
    public void selectByProvincia (String provincia) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 

        Query <Empleado> query = sn.createQuery("FROM Empleado e WHERE e.direccion.provincia=:provinciaArgs",Empleado.class);
        query.setParameter("provinciaArgs", provincia);
        List <Empleado> listaResultante = query.getResultList();
        for (Empleado e : listaResultante) {
            e.mostrarDatos();
        }

        tn.commit(); 
        sn.close();
    }

    public void selectByLocalidad (String localidad) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Query <Empleado> query = sn.createNamedQuery("Empleado.findByLocalidad", Empleado.class);
        query.setParameter("localidadArgs", localidad);
        List <Empleado> listaResultante = query.getResultList();
        for (Empleado e : listaResultante) {
            e.mostrarDatos();
        }
        tn.commit(); 
        sn.close();
    }


    public void actualizarUsuario (String nombre, String localizacion) {
        Empleado empleado = seleccionarNombre(nombre);
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        empleado.getDireccion().setLocalidad(localizacion);
        sn.merge(empleado); 
        tn.commit(); 
        sn.close();
    }

    public Empleado seleccionarNombre (String nombre) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Query <Empleado> query = sn.createQuery("FROM Empleado e WHERE e.nombre = :nombreArgs", Empleado.class);
        query.setParameter("nombreArgs", nombre);
        Empleado empleado = query.getSingleResult();
        tn.commit(); 
        sn.close();
        if (empleado != null) {
            return empleado;
        }
        else {
            return null;
        }
    }

    public void getAllClientes(int id) {
        sn = new Configuration().configure().buildSessionFactory().openSession();
        tn = sn.beginTransaction();
        Empleado emp = sn.find(Empleado.class, id);
        for (Cliente c : emp.getClientes()) {
            c.mostrarDatos();
        }
        tn.commit();
        sn.close();
    }
}
