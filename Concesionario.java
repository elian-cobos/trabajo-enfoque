import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Concesionario{
    private Semaphore semaphore;
    private boolean[] cochesOcupados;

    public Concesionario(int numeroCoches){
        semaphore=new Semaphore(numeroCoches);
        cochesOcupados=new boolean[numeroCoches];
    }

    public synchronized int usarCoche() throws InterruptedException{
        for(int i=0; i<cochesOcupados.length; i++){
            if (!cochesOcupados[i]){
                cochesOcupados[i]=true;
                return i + 1;
            }
        }
        return -1;
    }

    public synchronized void liberarVehiculo(int numero) {
        cochesOcupados[numero - 1]=false;
    }
    public Semaphore getSemaphore() {
        return semaphore;
    }
}
