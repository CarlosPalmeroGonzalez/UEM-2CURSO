/* =============== EVALUABLE 02 - PROGRAMACION DE SERVICIOS Y PROCESOS ===============
Sockets

El servidor de loterías y apuestas del Estado tiene un archivo con el número premiado de la lotería Nacional del día de hoy.
El cliente se conectará contra el servidor y le pasará el número que jugaba hoy para dicho sorteo (número de cinco dígitos).
El servidor deberá contestar si ha sido el afortunado apremiado o no.

Se tendrá en cuenta si se añade entorno gráfico desde el cliente.

*/
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import controller.ComprobarLoteria;
import network.socketTCPServidorLoteria;

public class Servidor {
    public static void main(String[] args) throws Exception {
        /* Declaración de variables */
        String num;
        boolean continuar, premiado;
        BufferedReader buffReader = null;
        PrintWriter printWriter = null;
        Path path = Paths.get("actividadesEvaluables\\evaluable02\\resources\\numeroPremiado.txt");
        ComprobarLoteria comprobacion = new ComprobarLoteria();

        /* Declaración e instanciación de nuestra clase socket del servidor */
        socketTCPServidorLoteria servidor = new socketTCPServidorLoteria(5000);

        try {
            /* Arrancamos el método start() de nuestro socker servidor */
            servidor.start();

            /* Instanciamos los objetos que nos permite la lectura y escritura pasandole el input y output de nuestro socker servidor */
            buffReader = new BufferedReader(new InputStreamReader(servidor.getInput()));
            printWriter = new PrintWriter(new OutputStreamWriter(servidor.getOutput()), true);

            /* Recibimos el numero de loteria del cliente y realizamos dos comprobaciones: que sean 5 dígitos y si es el número premiado o no */
            num = buffReader.readLine();

            /* Comprobamos que el número tenga 5 dígitos */
            System.out.println("Comprobando si tiene 5 dígitos el número: " + num);
            continuar = comprobacion.numeroCincoDigitos(num);
            if (continuar != true) {
                printWriter.println(" no es válido. El número debe contener 5 dígitos.");
            }

            /* Comprobamos si el número enviado por el cliente es el premiado o no */
            System.out.println("Comprobando premio del número: " + num);
            premiado = comprobacion.numeroPremiado(path, num);
            if (premiado == true){
                printWriter.println(" HA SIDO EL GANADOR DEL GORDO DE NAVIDAD!");
            }
            else {
                printWriter.println(" no ha sido premiado. Intentelo del año que viene.");
            }

            /* Ceramos nuestro socket cliente con el método stop() */
            servidor.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                printWriter.close();
                buffReader.close();
            } catch (NullPointerException e) {
                System.out.println("ERROR de NullPointerException al cerrar el PrintWriter del servidor.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("ERROR de IOException al cerrar el BufferedReader del servidor");
                e.printStackTrace();
            }
        }
    }
}
