package com.example.sesion06.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.example.sesion06.model.Empleado;


public class EmpleadoDAOImp {
    private Session sn;
    private Transaction tn;
    // No se necesta un session factory, porque es el intermediario para crear la session.

    public void insertarUsuario (Empleado emp) {
        // CAPA LÓGICA DE LA APP
        // Creamos el session factory dentro de nuestra <Session> y le metemos el open para abrirlo (esto es para resumirlo a la hora de explicar la clase)
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); // Comenzamos la transaccion
        sn.persist(emp); // Ejecutamos la session pasando el objeto de la clase <Empleado>
        tn.commit(); // Garantizamos la transacción
        sn.close(); // Cerramos la session
    }

    // Borrar por el ID
    public void borrarUsuario(int id) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        // Formato SQL => SELECT FROM empleados WHERE id = id
        // Formato Hibernate => Instanciamos un empleado igualandolo a la session y utilizando el método <find> que pide dos parámetros:
        // 1. La clase con la que vamos a trabajar
        // 2. El identificador que vamos a buscar (en este caso, el id)
        Empleado emp = sn.find(Empleado.class, id); // Con esto, obtengo el empleado correspondiente
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
        
        /* Porque funciona esto sin necesidad de sacar datos del objeto, guardarlos, etc..?
        Porque cuando utilizamos el método <find>, <remove>, etc.. => hasta que no hagas un commit, la entiedad (es decir, nuestra clase que tiene @Entity) se queda "en el aire"
        sin llegar a estar "persistido" (método persist). Todo lo que hagamos con esta entidad está pegada a la conexión con la base de datos que estamos trabajando.
        En resumidas cuentas: cuando realizamos el "commit" es como si realizaramos una actualización.
        */
        Empleado emp = sn.find(Empleado.class, id);
        
        if (emp != null) {
            /* Una forma de actualizar:
            Direccion direccion = emp.getDireccion(); // Obtenemos los atributos de direccion de nuestro objeto empleado
            direccion.setCalle(calleNueva); // modificaciones la dirección de la calle introducciendo la variable con la calle nueva
            emp.setDireccion(direccion); // modificacion el objeto direccion de nuestro empleado
            */
            // Otra forma de actualizar:
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
        // Podría crear una clase de "HibernateUtils" para que estos métodos que se repiten se hagan de golpe y no tener ni que volver a escribirlos
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        Empleado emp = sn.find(Empleado.class, id);
        emp.mostrarDatos();      
        tn.commit(); 
        sn.close();
    }

    // Seleccion sin usar el ID
    public void seleccionAll() {
        // Formato JDBC => PS -> RS -> WHILE -> OBJECT -> ARRAYLIST (JALEO MARINERO)
        // CriteriaQuery ó MutationQuery (HQL - JPQL)

        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        // Lo que queremos hacer es esto => SELECT * FROM empleados
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
        query.setParameter("provinciaArgs", provincia); // Aquí definimos el elemento parametrizado que falta por rellenar <provinciaArgs>
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

        // Ponemos el nombre de nuestra query y la clase en la que va a realizar el mapeo.
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
        sn.merge(empleado); // Este método sive para sincronizar un objeto "desconectado" con la base de datos => y está desconectado porque hemos cerrado la transacción en el método anterior
        tn.commit(); 
        sn.close();
    }

    public Empleado seleccionarNombre (String nombre) {
        sn = new Configuration().configure().buildSessionFactory().openSession(); 
        tn = sn.beginTransaction(); 
        // Buscar el usuario con HQL
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
}
