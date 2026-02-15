package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class socketTCPCliente implements Runnable {
    /* ATRIBUTOS DE LA CLASE */
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    private String num;
    private String respuestaFinal;
    
    /* CONTRUCTORES DE LA CLASE */
    public socketTCPCliente (String serverIP, int serverPort, String num) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.num = num;
    }

    /* MÉTODOS DE LA CLASE */
    public void start () throws UnknownHostException, IOException {
        System.out.println("(Cliente) Establece conexión...");
        socket = new Socket();
        InetSocketAddress addres = new InetSocketAddress(serverIP, serverPort);
        socket.connect(addres);
        input = socket.getInputStream();
        output = socket.getOutputStream();
        System.out.println("(Cliente) Conexion establecida.");
    }

    public void stop () throws UnknownHostException, IOException {
        System.out.println("(Cliente) Cerrando conexión...");
        input.close();
        output.close();
        socket.close();
        System.out.println("(Cliente) Conexión cerrada.");
    }

    public String getRespuestaFinal() {
        return respuestaFinal;
    }

    @Override
    public void run() {
        /* Declaración de variables */
        String respuesta;
        BufferedReader buffReader = null;
        PrintWriter printWriter = null;

        try {
            /* Arrancamos con el método start() de nuestro socket cliente */
            start();

            /* Instanciamos los objetos que nos permiten la lectura y escritura pasandole el input y ouput de nuestro socket cliente */
            buffReader = new BufferedReader(new InputStreamReader(input));
            printWriter = new PrintWriter(new OutputStreamWriter(output), true);

            /* En este parte, enviamos el numero al servidor con el printwriter y recogemos la respuesta del servidor con el bufferedReader */
            printWriter.println(num);
            respuesta = buffReader.readLine();
            
            /* Mostramos por consola la respuesta recibida por parte del servidor  */
            this.respuestaFinal = "Loteria Nacional del Estado informa que: \n" + "El número " + num + respuesta;

            /* Cerramos nuestro socket cliente con el método stop() */
            stop();
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
