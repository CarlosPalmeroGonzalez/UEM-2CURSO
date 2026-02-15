package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.Scanner;
import network.socketTCPCliente;

public class Cliente {
    
    public static void main(String[] args) {
        /* Declaración de variables */
        String num;
        String respuesta;
        Scanner sc = new Scanner (System.in);
        BufferedReader buffReader = null;
        PrintWriter printWriter = null;

        /* Declaración e instanciación de nuestra clase socket del cliente */
        socketTCPCliente cliente = new socketTCPCliente("localhost",5000 );

        /* Realizamos la pregunta al usuario y recogemos su número */
        System.out.print("Introduzca su número de la Loteria Nacional del Estado: ");
        num = sc.next();

        try {
            /* Arrancamos con el método start() de nuestro socket cliente */
            cliente.start();

            /* Instanciamos los objetos que nos permiten la lectura y escritura pasandole el input y ouput de nuestro socket cliente */
            buffReader = new BufferedReader(new InputStreamReader(cliente.getInput()));
            printWriter = new PrintWriter(new OutputStreamWriter(cliente.getOutput()), true);

            /* En este parte, enviamos el numero al servidor con el printwriter y recogemos la respuesta del servidor con el bufferedReader */
            printWriter.println(num);
            respuesta = buffReader.readLine();
            
            /* Mostramos por consola la respuesta recibida por parte del servidor  */
            System.out.println("Loteria Nacional del Estado informa que: \n" + "El número " + num + ":"+ respuesta);

            /* Cerramos nuestro socket cliente con el método stop() */
            cliente.stop();
        } catch (UnknownHostException e) { 
            System.out.println("ERROR de UnkownHostException en el main Cliente");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR de IOException en el main Cliente");
            e.printStackTrace();
        }
        finally{
            try {
                printWriter.close();
                buffReader.close();
            } catch (NullPointerException e) {
                System.out.println("ERROR de NullPointerException al cerrar el PrintWriter del cliente.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("ERROR de IOException al cerrar el BufferedReader del cliente");
                e.printStackTrace();
            }
            
        }
    }  
}
