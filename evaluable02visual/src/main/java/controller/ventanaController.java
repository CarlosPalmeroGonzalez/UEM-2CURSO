package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import network.socketTCPCliente;


public class ventanaController {
    @FXML
    private TextField tfNumero;
    
    @FXML
    private TextField tfResultado;

    @FXML
    private void comprobacionNumero () {
        String numero = tfNumero.getText();
        socketTCPCliente clienteSocket = new socketTCPCliente("localhost",5000, numero);
        Thread hiloCliente = new Thread(clienteSocket);
        hiloCliente.start();
        try {
            hiloCliente.join();
        } catch (InterruptedException e) {
            System.out.println("ERROR de InterruptedException en el método comprobacionNumero de la clase ventanaController");
            e.printStackTrace();
        }
        mostrarAlerta(clienteSocket.getRespuestaFinal());
        tfNumero.clear();
    }
    
    private void mostrarAlerta (String mensaje) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle("RESULTADO");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
