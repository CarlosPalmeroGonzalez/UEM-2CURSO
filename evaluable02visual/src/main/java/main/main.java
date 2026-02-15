package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.socketTCPServidorLoteria;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("/fxml/ventana.fxml"));
        Scene scene = new Scene (fxmlLoader.load());

        primaryStage.setTitle("LOTERIA NACIONAL DEL ESTADO");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        /* Creamos un hilo del servidor para que comience y permita continuar con el resto */
        socketTCPServidorLoteria servidor = new socketTCPServidorLoteria(5000);
        new Thread(servidor).start();
        primaryStage.setOnCloseRequest(event -> {
            try {
                servidor.stop();
            } catch (IOException e) {
                System.out.println("ERROR al cerrar el socket del servidor en main.java");
                e.printStackTrace();
            }
        });
    }
}
