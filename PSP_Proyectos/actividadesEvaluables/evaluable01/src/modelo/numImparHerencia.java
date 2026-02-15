package modelo;

public class numImparHerencia extends Thread {

    /* ATRIBUTOS */
    protected int num, sumaNum;

    /* CONSTRUCTORES */
    public numImparHerencia(int num) {
        this.num = num;
    }

    /* MÉTODOS */
    public void run() {
        for (int i=1; i<num; i++) {
            if (i%2 != 0) {
                System.out.printf("Hilo Herencia Impar => Número: %d %n", i);
                sumaNum += i;
            }
        }
        System.out.printf("Hilo Herencia Impar => SUMATORIO: %d %n", sumaNum);
    }
}
