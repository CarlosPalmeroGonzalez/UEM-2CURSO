package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ComprobarLoteria {
    public boolean numeroCincoDigitos(String numeroCliente) {
        if (numeroCliente.length() == 5) {
            return true;
        }
        return false;
    }

    public boolean numeroPremiado(Path path, String numeroCliente) {
        if (Files.exists(path)) {
            try (BufferedReader buffReader = new BufferedReader ( new FileReader(path.toFile()) ) ) {
                String numPremiado = buffReader.readLine();
                if (numPremiado.equals(numeroCliente)) {
                    return true;
                }
            } catch (IOException e) {
                System.out.println("ERROR de IOException en la clase de comprobación, método numeroPremiado.");
            }
        }
        return false;
    }
}
