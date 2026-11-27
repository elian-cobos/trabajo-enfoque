public class Main {

    public static void main (String[] args) {
        Concesionario concesionario = new Concesionario(4);

        for (int i = 1; i <= 9; i++) {
            Cliente cliente = new Cliente("cliente_" + i, concesionario);

            Thread hilo = new Thread(cliente);
            hilo.start();
        }



    }
}