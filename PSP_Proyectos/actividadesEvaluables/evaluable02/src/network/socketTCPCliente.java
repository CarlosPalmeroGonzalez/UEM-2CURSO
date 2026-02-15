package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class socketTCPCliente {
    /* ATRIBUTOS DE LA CLASE */
    private String serverIP;
    private int serverPort;
    private Socket socket;
    protected InputStream input;
    protected OutputStream output;
    
    /* CONTRUCTORES DE LA CLASE */
    public socketTCPCliente (String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
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

    /* MÉTODOS GETTERS Y SETTERS */

    public InputStream getInput() {
        return input;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    public OutputStream getOutput() {
        return output;
    }

    public void setOutput(OutputStream output) {
        this.output = output;
    }
}
