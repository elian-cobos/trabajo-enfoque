import java.util.concurrent.Semaphore;


public class Cliente implements Runnable{
    int tiempo = (int) (Math.random() * (10000 - 2000 + 1)) + 2000;
    private Concesionario concesionario;
    private String nombre;


    public Cliente(String nombre, Concesionario concesionario) {
        this.nombre = nombre;
        this.concesionario = concesionario;
    }

    @Override
    public void run() {
        Semaphore semaphore=concesionario.getSemaphore();
        boolean permisoAsignado = false;
        int vehiculo=-1;
        try{
            semaphore.acquire();
            permisoAsignado=true;
            vehiculo = concesionario.usarCoche();
            System.out.println(nombre + " ... probando vehículo ... " + vehiculo);
            Thread.sleep(tiempo);
            System.out.println(nombre + " ... terminó de probar el vehículo ... " + vehiculo);
        }catch(InterruptedException e){
            System.out.println(nombre + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }finally {
            if(vehiculo != -1){
                concesionario.liberarVehiculo(vehiculo);
            }
            if(permisoAsignado){
                semaphore.release();
            }
        }

    }
}
