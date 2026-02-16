package com.example.sesion06;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.example.sesion06.model.Direccion;
import com.example.sesion06.model.Empleado;
import com.example.sesion06.model.Habitacion;

public class Main {
    public static void main(String[] args) {
        // INSERT INTO empleados (nombre, apellido, correo, salario) VALUES ("", "", "", 0000)
        Empleado empleado = new Empleado ("Carlos", "Palmero", "carlos@gmail.com", 25000, 
            new Direccion("SC de Tenerife", "SC de Tenerife", "C/ Tenerife") ,"Becario");
        Habitacion habitacion = new Habitacion (101,5,1);

        // 1 => Creas una session factory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // 2 => Crea la sesión
        Session session = sessionFactory.openSession();
        // 3 => EntityManager -> Transaction 
        Transaction transaction = session.beginTransaction();
        // 4 => Trabaja con la transaccion
        session.persist(empleado); // Si queremos guardar algo, es el método <persist>
        // 5 => Garantiza la transaccion
        transaction.commit();
        // 6=> Cierra flujos de datos
        session.close();
        sessionFactory.close();
        
        /*
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.persist(habitacion);
        transaction.commit();
        session.close();
        sessionFactory.close();
        */
    }
}