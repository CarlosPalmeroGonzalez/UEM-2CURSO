package com.evaluables.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.evaluables.dao.EmpleadoDAOImp;
import com.evaluables.dao.PedidoDAOImp;
import com.evaluables.dao.ProductoDAOImp;
import com.evaluables.message.Mensajes;
import com.evaluables.model.Empleado;
import com.evaluables.model.Producto;

public class AlmacenController {
    /* ATRIBUTOS DE LA CLASE */
    // Declaración de variables de la clase
    private int opcionMenu;
    // Declaración de clase y objetos en la clase <almacenController>
    private Mensajes mj = new Mensajes();
    private Scanner sc = new Scanner (System.in);
    private ProductoDAOImp productoDAO = new ProductoDAOImp();
    private EmpleadoDAOImp empleadoDAO = new EmpleadoDAOImp();
    private PedidoDAOImp pedidoDAO = new PedidoDAOImp();
    // private ArrayList <Producto> lista = new ArrayList <> ();

    // Inicio del programa donde escogeremos las opciones de la parte lógica del almacén
    public void almacenControllerGestion () {
        do { 
            mj.menu();
            opcionMenu = sc.nextInt();
            switch (opcionMenu) {
                case 1: actualizarProductos();
                    break;
                case 2 : mostrarProductos();
                    break;
                case 3: mostrarProductosFav();
                    break;
                case 4: mostrarProductos600();
                    break;
                case 5: insertarEmpleado();
                    break;
                case 6: realizarPedido();
                    break;
                case 7 : System.out.println("Saliendo del programa almacén.");
                    break;
                default:
                    System.out.println("ERROR. Escoja una de las opciones del menú.");
            } 
        } while (opcionMenu !=7);
    }

    /* CASE 01 => ACTUALIZACIÓN DE LA BASE DE DATOS IMPORTANDO LOS VALORES DEL JSON */
    public void actualizarProductos () {
        try {
            productoDAO.insertarProductos();
            System.out.println("Se han actualizado los productos en la base de datos.");
        } catch (SQLException e) {
            System.err.println("La base de datos ya se encuentra actualizada. ");
        } 
    }

    /* CASE 02 => MOSTRAMOS TODOS LOS PRODUCTOS DE NUESTRA BASE DE DATOS */
    public void mostrarProductos () {
        mj.espacio();
        System.out.println("TODOS LOS PRODUCTOS");
        mj.saltoLinea();
        for (Producto p : productoDAO.listaProductos()) {
            System.out.println(p);
        }
        mj.saltoLinea();
    }

    /* CASE 03 => MOSTRAMOS TODOS LOS PRODUCTOS FAVORITOS (PRECIO > 1000) DE NUESTRA BASE DE DATOS  */
    public void mostrarProductosFav () {
        mj.espacio();
        System.out.println("PRODUCTOS FAVORITOS");
        mj.saltoLinea();
        for (Producto p : productoDAO.listaProductosFav()) {
            System.out.println(p);
        }
    }

    /* CASE 04 => MOSTRAMOS TODOS AQUELLOS PRODUCTOS DE MENOS DE 600 EUROS DE NUESTRA BASE DE DATOS */
    public void mostrarProductos600() {
        mj.espacio();
        System.out.println("PRODUCTOS CON PRECIO INFERIOR A 600,00 EUROS");
        mj.saltoLinea();
        for (Producto p : productoDAO.listaProductos()) {
            if (p.getPrecio() < 600) {
                System.out.println(p);
            }
        }
        mj.saltoLinea();
    }

    /* CASE 05 => AGREGAMOS UN NUEVO EMPLEADO EN NUESTRA BASE DE DATOS */
    public void insertarEmpleado() {
        boolean fallo = false;
        Empleado emp = new Empleado();
        mj.espacio();
        mj.saltoLinea();
        System.out.println("Inserta los siguientes datos del empleado:");
        System.out.print("Nombre: ");
        String nombre = sc.next();
        System.out.print("Apellido: ");
        String apellido = sc.next();
        System.out.print("Correo: ");
            String correo = sc.next();
            emp = new Empleado (nombre, apellido, correo);
        do {
            try {
                if (fallo == true) {
                    emp.setCorreo(correo);
                }
                empleadoDAO.insertarEmpleado(emp);
                fallo = false;
            } catch (SQLException e) {
                System.out.println("Correo duplicado.");
                System.out.print("Indica un correo nuevo: ");
                correo = sc.next();
                fallo = true;
            }
        } while (fallo == true);
        System.out.println("Empleado creado correctamente.");
    }

    /* CASE 06 => REALIZAMOS PEDIDOS SELECCIONANDO EMPLEADOS Y PRODUCTOS PARA REGISTRARLOS EN NUESTRA BASE DE DATOS*/
    public void realizarPedido() {
        Empleado emp = new Empleado();
        Producto pro = new Producto();
        mj.espacio();
        mj.saltoLinea();
        System.out.println("LISTA DE EMPLEADOS");
        mj.saltoLinea();
        mostrarEmpleados();
        mj.saltoLinea();
        System.out.print("ID del empleado: ");
        int opcion = sc.nextInt();
        for (Empleado e : empleadoDAO.mostrarEmpleados()) {
            if (e.getId() == opcion) {
                emp = e;
                break;
            }
        }
        mj.espacio();
        mostrarProductos();
        System.out.print("ID del producto: ");
        opcion = sc.nextInt();
        System.out.print("Unidades del producto: ");
        int cuantia = sc.nextInt();
        for (Producto p : productoDAO.listaProductos()) {
            if (p.getId() == opcion) {
                pro = p;
                break;
            }
        }
        System.out.print("Nombre característico para el pedido: ");
        String nombre = sc.next();
        Boolean registrado = pedidoDAO.realizarPedido(pro, emp, cuantia, nombre);
        if (registrado) {
            System.out.println("Pedido realizado satisfactoriamente. ");
        }
        else {
            System.out.println("Pedido no registrado. ");
        }
        
        
    }

    public void mostrarEmpleados() {
        empleadoDAO.mostrarEmpleados();
        for (Empleado e : empleadoDAO.mostrarEmpleados()) {
            System.out.println(e);
        }
    }
}
