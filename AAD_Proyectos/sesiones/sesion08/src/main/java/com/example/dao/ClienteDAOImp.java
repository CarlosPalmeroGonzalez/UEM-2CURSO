package com.example.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.model.Cliente;
import com.example.model.Empleado;


public class ClienteDAOImp {
    private Session session;
    private Transaction transaction;

    public void getAllEmpleadosReserva(int id) {
        session = new Configuration().configure().buildSessionFactory().openSession();
        transaction = session.beginTransaction();

        /* Para sacar todos los empleados que gestionan las reservas de un cliente:
        Necesito cliente de la tabla -> id -> metodo find -> obtienes objeto CLIENTE -> y consigues el hashset <empleados> */
        Cliente cl = session.find(Cliente.class, id);
        // cl.getEmpleados();  =>  Con esto, obtendríamos todos los empleados gracias al hashset
        
        /*for (Empleado emp : cl.getEmpleados()) {
            emp.mostrarDatos();
        }*/

        /* Otra manera de obtener los empleados => con funciones LAMBDA */
        // cl.getEmpleados().forEach(item -> item.mostrarDatos()); // =>  Esto es lo mismo que lo de antes del <for>
        cl.getEmpleados().forEach(Empleado::mostrarDatos); // => En las funciones LAMBDA si hay un método que no recibe parametros, se puede llamar de forma directa

        transaction.commit();
        session.close();
    }

    public void realizarReserva(int idCliente, int idEmpleado) {
        session = new Configuration().configure().buildSessionFactory().openSession();
        transaction = session.beginTransaction();

        Cliente c = session.find(Cliente.class, idCliente);
        Empleado e = session.find(Empleado.class, idEmpleado);
        c.addEmpleado(e); // => Con esto estaríamos rellenando la tabla de reservas
        
        transaction.commit();
        session.close();
    }
}
