package com.sesiones.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import com.sesiones.Model.Usuario;

public class OperacionesFicheros {

    public void lecturaFicheros (String path) {
        File archivo =  new File(path); // Si la ruta path existe, puedo sacar toda la información del fichero
        /* System.out.println(archivo.canExecute());  => Aqui nos devolverá true si puede ejecutar el archivo o false si no puede ejecutarlo 
        if (!archivo.exists()) { => si el fichero no existiera, deberiamos realizar esta sentencia para crearlo.
            archivo.createNewFile(); } 
        Pero aquí vamos a partir de la base que siempre van a existir los ficheros.
        */
        FileReader fileReader = null; // El FileReader nos permite hacer una lectura LETRA A LETRA
        try {
            fileReader = new FileReader(archivo); // El filereader nos pide un objeto de tipo parametro, es decir, nuestro fichero
            /*int numero = fileReader.read();
            System.out.println((char)numero); // => Ponemos char para pasar el número a letra */
            int numero = -1; // => Se pone -1 porque los numeros negativos no están en la tabla ASCII
            while ((numero = fileReader.read()) != -1) { // Mientras hagas la lectura y no devuelvas un -1, sigue realizando la lectura 
                //System.out.println(numero); // => Aquí realiza la lectura de todos los número de tipo ASCII de fichero
                System.out.print((char)numero);
                
                /* FORMA DE LEER UN MENSAJE CIFRADO */
                // System.out.print( (char) (numero/9) ); // => Primero divide entre 9 y después pasalo a char
            }
        } catch (IOException e) {
            // throw new RuntimeException(e); => Si dejamos este throw, haremos que el programa no se pare y pueda tirar la exception y parar el programa
            // Por lo tanto, ponemos el tratamiento de la excepcion para informar
            System.out.println("No puedes hacer la lectura");
        } finally {
            try {
                fileReader.close();
            } catch (IOException | NullPointerException e) { // Se pone un solo <|> porque comprobamos todo. Porque al poner <||> desde que una sea correcta, entra y no comprueba las siguientes 
                System.out.println("Error al cerrar el FileReader");
            }
        }
    }
    public void lecturaFicherosBuffer(String path) {
        File archivo = new File(path);
        BufferedReader buffReader = null; // Aqui leeremos fila a fila, en vez de letra a letra como en el anterior
        try {
            buffReader = new BufferedReader(new FileReader(archivo)); // Realizamos el bufferReader sobre el FileReader en el archivo enviado 
            String linea = null; // => Aquí no se pone numero porque estamos leyendo variables complejas
            while ( (linea = buffReader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("No puedes hacer la lectura");
        } finally {
            try {
                buffReader.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar el BufferedReader");
            }
        }
    }
    public void escrituraSimple(String path) {
        File archivo = new File(path);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("La creación no se puede dar.");
            }
        }

        FileWriter fileWriter = null;
        String mensaje = "El enunciado del examen es este: la primera pregunta es XXXX";
        
        try {
            fileWriter = new FileWriter(archivo, true); // => Aquí, se puede pasar un booleano que permite seguir completando la oracion escrita
            /* MÉTODO DE CIFRADO DE UN MENSAJE */
            for (int i=0; i<mensaje.length(); i++) {
                char letra = mensaje.charAt(i); // => Aquí obtenemos cada una de las letras del mensaje
                fileWriter.write((int)letra*9) ; // => Con la letra, obtenemos su numero ASCII y lo multiplicamos x9 para que el mensaje esté cifrado
            }

            // fileWriter.write("65"); //=> Método para escribir 
            // fileWriter.append(); //=> Otra manera de escribir con el fileWriter
        } catch (IOException e) {
            System.out.println("Error al intentar escribir en el archivo.");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error al cerrar el FileWriter.");
            }
        }
    }
    public void escrituraCompleja(String path){
        File archivo = new File(path);
        if(!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error el crear el archivo de escritura.");
            }
        }
        // BufferedWriter => Se puede utilizar este o el PrintWriter, pero el PrintWriter es más eficiente
        PrintWriter printWriter = null;
        
        try {
            printWriter = new PrintWriter(new FileWriter(archivo,true)); // => le ponemos el true para que sigue sumando lineas
            printWriter.println("Esta es la primera línea."); // Aquí escribimos la linea y al poner <println> realiza el salto de linea (en el BW sería con <newLine()>)
            printWriter.println("Esta es la segunda línea.");
        } catch (IOException e) {
            System.out.println("No hay permisos de IO");
        } finally {
            try {
                printWriter.close(); // La diferencia entre el BufferWriter y el PrintWriter, es que este último no te pide controlar si hay excepcion de IO
            } catch (NullPointerException e) { // Pero si puede dar error en los nulos con el PrintWriter
                System.out.println("Error en el cerrado.");
            }
        }
        
    }
    
    public void escrituraObjectos(String path){
        Usuario user = new Usuario(1, "Carlos", "Palmero", "carlos@gmail.com", "45897977M", 123);
        File archivo = new File(path);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("No tienes permisos de creación.");
            }
        }
        /* Realizamos un fichero .obj o .bin (porque son binarios) */
        ObjectOutputStream dos = null;

        try {
            dos = new ObjectOutputStream( new FileOutputStream(archivo)); // Como es un flujo de datos, prevenimos el posible fallo que pueda dar
            dos.writeObject(user);
        } catch (IOException ex) {
            System.out.println("Fichero indicado incorrecto.");
        } finally{
            try {
                dos.close();
            } catch (IOException | NullPointerException e) { // Prevenimos de la excepciones y de los nulos
                System.out.println("El fichero no se puede cerrar.");
            }
        }
        

    }
}
