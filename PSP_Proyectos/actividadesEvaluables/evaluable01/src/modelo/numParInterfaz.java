package modelo;

public class numParInterfaz implements Runnable{

    /* ATRIBUTOS */
    protected int num, sumaNum;

    /* CONSTRUCTORES */
    public numParInterfaz(int num) {
        this.num = num;
    }

    /* MÉTODOS */
    @Override
    public void run() {
        for (int i=1; i<num+1; i++) {
            if (i%2 == 0) {
                System.out.printf("Hilo Interfaz Par => Número: %s %n", i);
                sumaNum += i;
            }
        }
        System.out.printf("Hilo Interfaz Par => SUMATORIO: %d %n", sumaNum);
    }
}
