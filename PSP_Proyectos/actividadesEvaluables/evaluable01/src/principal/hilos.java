package principal;

import modelo.*;

public class hilos {
    public static void main(String[] args) {
        final int DIEZ = 10;

        // Instanciamos las clases 
        numImparHerencia numImparHerencia = new numImparHerencia(DIEZ);
        numParInterfaz numParInterfaz = new numParInterfaz (DIEZ);

        // Hilos generados a través de la clase Thread
        numImparHerencia.start();
        // Hilos generados con la interfaz Runnable
        new Thread(numParInterfaz).start();
    }
}
