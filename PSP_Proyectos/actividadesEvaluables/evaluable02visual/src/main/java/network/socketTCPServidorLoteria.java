package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

import database.ComprobarLoteria;

public class socketTCPServidorLoteria implements Runnable {
    /* ATRIBUTOS DE LA CLASE */
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    /* CONTRUCTORES DE LA CLASE */
    public socketTCPServidorLoteria (int puerto) throws IOException{
        serverSocket = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress("localhost",puerto);
        serverSocket.bind(address,3000);
    }

    /* MÉTODOS DE LA CLASE */
    public void start() throws IOException{
        System.out.println("(Servidor Loterias) Esperando conexión del servidor...");
        socket = serverSocket.accept();
        input = socket.getInputStream();
        output = socket.getOutputStream();
        System.out.println("(Servidor Loterias) Conexión realizada.");
    }

    public void stop () throws IOException {
        System.out.println("(Servidor Loterias) Cerrando dependencias del servidor...");
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor Loterias) Dependencias cerradas.");
    }

    @Override
    public void run() {
        /* Declaración de variables */
        String num;
        boolean continuar, premiado;
        BufferedReader buffReader = null;
        PrintWriter printWriter = null;
        Path path = Paths.get("actividadesEvaluables\\evaluable02visual\\src\\main\\resources\\data\\numeroPremiado.txt");
        ComprobarLoteria comprobacion = new ComprobarLoteria();
        try {
            /* Arrancamos el método start() de nuestro socker servidor */
            start();
            
            /* Instanciamos los objetos que nos permite la lectura y escritura pasandole el input y output de nuestro socker servidor */
            buffReader = new BufferedReader(new InputStreamReader(input));
            printWriter = new PrintWriter(new OutputStreamWriter(output), true);
            
            /* Recibimos el numero de loteria del cliente y realizamos dos comprobaciones: que sean 5 dígitos y si es el número premiado o no */
            num = buffReader.readLine();
            
            /* Comprobamos que el número tenga 5 dígitos */
            System.out.println("Comprobando si tiene 5 dígitos el número: " + num);
            continuar = comprobacion.numeroCincoDigitos(num);
            if (continuar != true) {
                printWriter.println(" no es válido.\n El número debe contener 5 dígitos.");
            }
            
            /* Comprobamos si el número enviado por el cliente es el premiado o no */
            System.out.println("Comprobando premio del número: " + num);
            premiado = comprobacion.numeroPremiado(path, num);
            if (premiado == true){
                printWriter.println(" HA SIDO EL GANADOR DEL GORDO DE NAVIDAD!");
            }
            else {
                printWriter.println(" no ha sido premiado.\n Intentelo del año que viene.");
            }
            
            
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
