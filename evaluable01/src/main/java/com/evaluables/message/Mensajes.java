package com.evaluables.message;

public class Mensajes {

    public void menu() {
        espacio();
        System.out.println("|=================== MENU ====================|");
        System.out.println("| 1.- Actualizar productos                    |");
        System.out.println("| 2.- Mostrar todos los productos             |");
        System.out.println("| 3.- Mostrar todos los porductos favoritos   |");
        System.out.println("| 4.- Mostrar productos menores a 600 euros   |");
        System.out.println("| 5.- Agregar un nuevo empleado               |");
        System.out.println("| 6.- Realizar un pedido                      |");
        System.out.println("| 7.- Salir                                   |");
        System.out.println("|=============================================|");
        escogerOpcion();
    }

    public void escogerOpcion() {
        System.out.print("Escoja una opción del menú: ");
        espacio();
    }
    
    public void saltoLinea () {
        System.out.println("===============================================");
    }

    public void espacio() {
        System.out.println("");
    }

    public void bienvenida () {
        System.out.println("Bienvenido al Almacen de Productos DUMMYJSON");
        saltoLinea();
    }
    
    public void despedida() {
        saltoLinea();
        System.out.println("¡Hasta pronto!");
        espacio();
    }
}
