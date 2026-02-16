package com.sesiones;

import com.sesiones.Controller.OperacionesFicheros;

public class Main {
    public static void main(String[] args) {
        OperacionesFicheros operaciones = new OperacionesFicheros(); // Invocamos e instanciamos el objeto
        //operaciones.lecturaFicheros("sesiones\\demo\\src\\main\\java\\com\\sesiones\\Ficheros\\ejemplo.txt"); // => Le pasamos el path de donde se encuentra el fichero
        //operaciones.lecturaFicherosBuffer("sesiones\\demo\\src\\main\\java\\com\\sesiones\\Ficheros\\ejemplo.txt");
        //operaciones.escrituraSimple("sesiones\\demo\\src\\main\\java\\com\\sesiones\\Ficheros\\escritura.txt");
        //operaciones.escrituraCompleja("sesiones\\demo\\src\\main\\java\\com\\sesiones\\Ficheros\\escrituraCompleja.txt");
        //operaciones.escrituraObjectos("sesiones\\demo\\src\\main\\java\\com\\sesiones\\Ficheros\\escrituraObjetos.obj");
        //operaciones.escrituraObjectosMultiple("sesiones\\sesion03\\src\\main\\java\\com\\sesiones\\Ficheros\\escrituraObjetos.obj");
        operaciones.lecturaObjeto("sesiones\\sesion03\\src\\main\\java\\com\\sesiones\\Ficheros\\escrituraObjetos.obj");

        /* En el paquete "Model"  se ponen todos los modelos que quieres que formen parte de tu lógica (un usuario, un informe, una dirección) */

    }
}