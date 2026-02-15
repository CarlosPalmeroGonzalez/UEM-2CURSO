package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class socketTCPServidorLoteria {
    /* ATRIBUTOS DE LA CLASE */
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream input;
    private OutputStream output;

    /* CONTRUCTORES DE LA CLASE */
    public socketTCPServidorLoteria (int puerto) throws IOException{
        serverSocket = new ServerSocket();
        InetSocketAddress address = new InetSocketAddress("localhost",puerto);
        serverSocket.bind(address);
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
        System.out.println("(Servidor Loterias) Cerrando conexión con el servidor...");
        input.close();
        output.close();
        socket.close();
        serverSocket.close();
        System.out.println("(Servidor Loterias) Conexión cerrada.");
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
